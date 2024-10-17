package com.postgre.springapipostgre.DTO.base;

import java.time.LocalDateTime;
import java.util.List;

public class MouNdaDTO {
    private Long id;
    private String base; // Menambahkan field 'base' yang ada di serializer Django
    private String partnershipTitle;
    private String background;
    private String note;
    private Long userId;
    private String partnershipCandidate; // Jika ini adalah kandidat kerjasama
    private String status; // Pastikan untuk menggunakan enum jika ada
    private String responseText; // Menambahkan response text
    private String approvalNote; // Menambahkan approval note
    private String mouNdaNumber; // Menambahkan nomor MOU NDA
    private LocalDateTime approvalCompletionDate; // Menambahkan tanggal penyelesaian approval
    private Long officialUndersign; // Mengacu pada ID official undersign
    private List<ScopeDTO> scopesMou; // Daftar scope
    private List<AttachmentDTO> attachmentsMou; // Daftar lampiran

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public String getPartnershipTitle() {
        return partnershipTitle;
    }

    public void setPartnershipTitle(String partnershipTitle) {
        this.partnershipTitle = partnershipTitle;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPartnershipCandidate() {
        return partnershipCandidate;
    }

    public void setPartnershipCandidate(String partnershipCandidate) {
        this.partnershipCandidate = partnershipCandidate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getApprovalNote() {
        return approvalNote;
    }

    public void setApprovalNote(String approvalNote) {
        this.approvalNote = approvalNote;
    }

    public String getMouNdaNumber() {
        return mouNdaNumber;
    }

    public void setMouNdaNumber(String mouNdaNumber) {
        this.mouNdaNumber = mouNdaNumber;
    }

    public LocalDateTime getApprovalCompletionDate() {
        return approvalCompletionDate;
    }

    public void setApprovalCompletionDate(LocalDateTime approvalCompletionDate) {
        this.approvalCompletionDate = approvalCompletionDate;
    }

    public Long getOfficialUndersign() {
        return officialUndersign;
    }

    public void setOfficialUndersign(Long officialUndersign) {
        this.officialUndersign = officialUndersign;
    }

    public List<ScopeDTO> getScopesMou() {
        return scopesMou;
    }

    public void setScopesMou(List<ScopeDTO> scopesMou) {
        this.scopesMou = scopesMou;
    }

    public List<AttachmentDTO> getAttachmentsMou() {
        return attachmentsMou;
    }

    public void setAttachmentsMou(List<AttachmentDTO> attachmentsMou) {
        this.attachmentsMou = attachmentsMou;
    }

}
