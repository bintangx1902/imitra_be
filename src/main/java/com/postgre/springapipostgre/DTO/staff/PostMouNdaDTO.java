package com.postgre.springapipostgre.DTO.staff;

import com.postgre.springapipostgre.DTO.base.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostMouNdaDTO {
    private String base;
    private String partnershipTitle;
    private String background;
    private String note;
    private Long userId; // assuming user is represented by ID
    private String status;
    private String responseText;
    private String approvalNote;
    private String mouNdaNumber;
    private LocalDateTime approvalCompletionDate;
    private Long officialUndersignId; // assuming Functionary is represented by ID
    private List<ScopeDTO> scopes; // List of ScopeDTO
    private List<AttachmentDTO> attachments; // List of AttachmentDTO

    // Getters and Setters
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getOfficialUndersignId() {
        return officialUndersignId;
    }

    public void setOfficialUndersignId(Long officialUndersignId) {
        this.officialUndersignId = officialUndersignId;
    }

    public List<ScopeDTO> getScopes() {
        return scopes;
    }

    public void setScopes(List<ScopeDTO> scopes) {
        this.scopes = scopes;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }
}
