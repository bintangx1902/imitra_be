package com.postgre.springapipostgre.DTO.manager;

public class MouNdaUpdateDTO {
    private String approvalNote;
    private String responseText;

    // Getters and Setters
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
