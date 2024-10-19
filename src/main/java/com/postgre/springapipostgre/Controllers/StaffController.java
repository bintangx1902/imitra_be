package com.postgre.springapipostgre.Controllers;

import com.postgre.springapipostgre.DTO.base.*;
import com.postgre.springapipostgre.Services.*;
import com.postgre.springapipostgre.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    // endpoint create Mou/Nda
    @PostMapping("/create-mou-nda")
    public ResponseEntity<MouNda> createMouNda(
            @ModelAttribute MouNdaDTO mouNdaDTO,
            @RequestParam("attachments") List<MultipartFile> attachments) throws IOException {

        MouNda createdMouNda = staffService.createMouNda(mouNdaDTO, attachments).getBody();
        return new ResponseEntity<>(createdMouNda, HttpStatus.CREATED);
    }

    // endpoint create PKS
    @PostMapping("/create-pks")
    public ResponseEntity<PKS> createPKS(
            @ModelAttribute PKSDTO pksdto,
            @RequestParam("attachments") List<MultipartFile> attachments) throws IOException {

        try {
            PKS createPks = staffService.createPks(pksdto, attachments).getBody();
            return new ResponseEntity<>(createPks, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new IllegalArgumentException("file upload failed" + e.getMessage());
            //            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
//        return new ResponseEntity<>(createPks, HttpStatus.CREATED);
    }

    // Endpoint untuk mengambil daftar MouNda berdasarkan filter
    @GetMapping("/moundas")
    public ResponseEntity<List<MouNdaDTO>> getAllMouNda(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String date) {

        List<MouNdaDTO> mouNdaList = staffService.getAllMouNda(type, date);
        return ResponseEntity.ok(mouNdaList);
    }

    // Endpoint untuk mengambil daftar PKS berdasarkan filter
    @GetMapping("/pkses")
    public ResponseEntity<List<PKSDTO>> getAllPKS(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String date) {

        List<PKSDTO> pksList = staffService.getAllPKS(type, date);
        return ResponseEntity.ok(pksList);
    }

    @GetMapping("/moundas/dashboard")
    public ResponseEntity<List<MouNdaDTO>> getAllMouNdaDashboard(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status) {

        List<MouNdaDTO> mouNdaDTOList = staffService.dashboardMouNda(type, status);
        return ResponseEntity.ok(mouNdaDTOList);
    }
}
