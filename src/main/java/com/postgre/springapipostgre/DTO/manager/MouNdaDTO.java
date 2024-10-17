package com.postgre.springapipostgre.DTO.manager;

public class MouNdaDTO {
    private Long id;
    private String partnershipTitle;
    private String background;
    private String note;
    private String partnershipCandidate;
    private String status;
    private String approvalNote;
    private String responseText;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getApprovalNote() {
        return approvalNote;
    }

    public void setApprovalNote(String approvalNote) {
        this.approvalNote = approvalNote;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}
