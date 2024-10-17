package com.postgre.springapipostgre.Services;

import com.postgre.springapipostgre.models.MouNda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.postgre.springapipostgre.DTO.base.*;
import com.postgre.springapipostgre.Repositories.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.postgre.springapipostgre.models.enums.*;
import com.postgre.springapipostgre.models.*;
import com.postgre.springapipostgre.utils.ConvertToDTO;

@Service
public class StaffServive {
    @Autowired
    private MouNdaRepository mouNdaRepository;

    @Autowired
    private PKSRepository pksRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScopeRepository scopeRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public void saveFile(MultipartFile file, String dir_target) throws IOException {
        String uploadDir = "media/" + dir_target;
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = uploadDir + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);
    }


    // Controller create-mou-nda
    public ResponseEntity<MouNda> createMouNda(MouNdaDTO mouNdaDto, List<MultipartFile> attachments) throws IOException {
        // Konversi DTO ke model dan simpan
        MouNda mouNda = new MouNda();

        User user = userRepository.findById(mouNdaDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        mouNda.setUser(user);

        mouNda.setBase(BaseChoices.valueOf(mouNdaDto.getBase()));
        mouNda.setPartnershipTitle(mouNdaDto.getPartnershipTitle());
        mouNda.setBackground(mouNdaDto.getBackground());
        mouNda.setNote(mouNdaDto.getNote());
        mouNda.setStatus(StatusChoices.valueOf(mouNdaDto.getStatus()));
        mouNda.setResponseText(mouNdaDto.getResponseText());
        mouNda.setApprovalNote(mouNdaDto.getApprovalNote());
        mouNda.setMouNdaNumber(mouNdaDto.getMouNdaNumber());
        mouNda.setApprovalCompletionDate(mouNdaDto.getApprovalCompletionDate());
        mouNda.setOfficialUndersign(null); // Asumsi official undersign sudah di-set dalam DTO

        // Simpan ke database
        mouNda = mouNdaRepository.save(mouNda);

        // Simpan scopes jika ada
        if (mouNdaDto.getScopesMou() != null) {
            for (ScopeDTO scopeDto : mouNdaDto.getScopesMou()) {
                Scope scope = new Scope();
                scope.setScopeName(scopeDto.getScopeName());
                scope.setMouNda(mouNda); // Hubungkan dengan MouNda
                scopeRepository.save(scope);
            }
        }

        // Simpan attachments jika ada
        if (attachments != null && !attachments.isEmpty()) {
            for (MultipartFile file : attachments) {
                saveFile(file, "attachments/");
                Attachment attachment = new Attachment();
                attachment.setFile(file.getOriginalFilename()); // Pastikan Anda menangani penyimpanan file di disk
                attachment.setMouNda(mouNda); // Hubungkan dengan MouNda
                attachmentRepository.save(attachment);
            }
        }
        return new ResponseEntity<>(mouNda, HttpStatus.CREATED);

    }

    // Controller for List all Mou/Nda
    public List<MouNdaDTO> getAllMouNda(String type, String date) {
        List<MouNda> mouNdaList = mouNdaRepository.findAll();

        // submission date filtering
        if (date != null) {
            try {
                LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                mouNdaList = mouNdaList.stream()
                        .filter(mouNda -> mouNda.getSubmissionDate().toLocalDate().isEqual(parseDate))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid date format");
            }
        }

        if (type != null) {
            mouNdaList = mouNdaList.stream()
                    .filter(mouNda -> mouNda.getBase().toString().equals(type))
                    .collect(Collectors.toList());
        }
        return ConvertToDTO.toMouNdaDTO(mouNdaList);
    }

    // Controller for List all PKS
    public List<PKSDTO> getAllPKS(String type, String date) {
        List<PKS> PKSList = pksRepository.findAll();

        // submission date filtering
        if (date != null) {
            try {
                LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                PKSList = PKSList.stream()
                        .filter(pks -> pks.getSubmissionDate().toLocalDate().isEqual(parseDate))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid date format");
            }
        }
        return ConvertToDTO.toPksDTO(PKSList);
    }
}
