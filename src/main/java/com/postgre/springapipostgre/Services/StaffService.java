package com.postgre.springapipostgre.Services;

import com.postgre.springapipostgre.models.*;
import com.postgre.springapipostgre.utils.*;
import com.postgre.springapipostgre.utils.FileControl.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.postgre.springapipostgre.DTO.base.*;
import com.postgre.springapipostgre.Repositories.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.postgre.springapipostgre.models.enums.*;
import com.postgre.springapipostgre.utils.*;

@Service
public class StaffService {
    @Autowired
    private MouNdaRepository mouNdaRepository;

    @Autowired
    private PKSRepository pksRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScopeRepository scopeRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private RabRepository rabRepository;


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
                SaveFile.save(file, "attachments/");
                Attachment attachment = new Attachment();
                attachment.setFile(file.getOriginalFilename()); // Pastikan Anda menangani penyimpanan file di disk
                attachment.setMouNda(mouNda); // Hubungkan dengan MouNda
                attachmentRepository.save(attachment);
            }
        }
        return new ResponseEntity<>(mouNda, HttpStatus.CREATED);

    }

    // Controller for Create PKS
    public ResponseEntity<PKS> createPks(PKSDTO pksDto, List<MultipartFile> attachments) throws IOException {
        PKS pks = new PKS();

        if (pksDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        User user = userRepository.findById(pksDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        pks.setUser(user);

        pks.setTitle(pksDto.getTitle());
        pks.setBackground(pksDto.getBackground());
        pks.setNote(pksDto.getNote());

        if (pksDto.getStatus() != null) {
            try {
                pks.setStatus(StatusChoices.valueOf(pksDto.getStatus()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status value");
            }
        }


        pks = pksRepository.save(pks);

        if (pksDto.getScopesPks() != null) {
            for (ScopeDTO scopeDto : pksDto.getScopesPks()) {
                Scope scope = new Scope();
                scope.setScopeName(scopeDto.getScopeName());
                scope.setPks(pks);
                scopeRepository.save(scope);
            }
        }

        if (pksDto.getRab() != null) {
            for (RabDTO rabDto : pksDto.getRab()) {
                RAB rab = new RAB();
                rab.setCustomer(rabDto.getCustomer());
                rab.setType(TypeChoices.valueOf(rabDto.getType()));
                rab.setRevenue(rabDto.getRevenue());
                rab.setCost(rabDto.getCost());
                rab.setCostDesc(rabDto.getCostDesc());
                rab.setPks(pks);

                rabRepository.save(rab);
            }
        }

        if (attachments != null) {
            for (MultipartFile file : attachments) {
                SaveFile.save(file, "pks/");
                Attachment attachment = new Attachment();

                String fileName = FileNameUtils.generateSafeFileName(Objects.requireNonNull(file.getOriginalFilename()));
                attachment.setFile(fileName);
                attachment.setPks(pks);
                attachmentRepository.save(attachment);
            }
        }

        return new ResponseEntity<>(pks, HttpStatus.CREATED);
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

    // Controller for dashboard endpoint (mou/Nda)
    public List<MouNdaDTO> dashboardMouNda(String type, String status) {
        List<MouNda> mouNdaList;

        if (status != null) {
            mouNdaList = mouNdaRepository.findByStatusIn(List.of(StatusChoices.R, StatusChoices.F));
        } else {
            mouNdaList = mouNdaRepository.findAll();
        }

        if (type != null && (type.equals("m") || type.equals("n"))) {
            mouNdaList = mouNdaList.stream()
                    .filter(mouNda -> mouNda.getBase().toString().equals(type))
                    .collect(Collectors.toList());
        }
        return ConvertToDTO.toMouNdaDTO(mouNdaList);
    }

    // Controller for dashboard endpoint (pks)
    public List<PKSDTO> dashboardPKS(String type, String status) {
        List<PKS> pksList;
        if (status != null) {
            pksList = pksRepository.findByStatusIn(List.of(StatusChoices.R, StatusChoices.F));
        } else {
            pksList = pksRepository.findAll();
        }

        return ConvertToDTO.toPksDTO(pksList);
    }
}
