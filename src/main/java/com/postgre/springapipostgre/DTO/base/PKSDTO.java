package com.postgre.springapipostgre.DTO.base;

import java.util.List;

public class PKSDTO {
    private Long id;
    private String partnershipType;
    private String budgetType;
    private String budgetNumber;
    private String partnershipMethod;
    private String materialType;
    private String title;
    private String background;
    private String note;
    private String partnershipCandidate;
    private String status;
    private List<RabDTO> rab;
    private List<ScopeDTO> scopesPks;
    private List<AttachmentDTO> attachmentsPks;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnershipType() {
        return partnershipType;
    }

    public void setPartnershipType(String partnershipType) {
        this.partnershipType = partnershipType;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public String getBudgetNumber() {
        return budgetNumber;
    }

    public void setBudgetNumber(String budgetNumber) {
        this.budgetNumber = budgetNumber;
    }

    public String getPartnershipMethod() {
        return partnershipMethod;
    }

    public void setPartnershipMethod(String partnershipMethod) {
        this.partnershipMethod = partnershipMethod;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<RabDTO> getRab() {
        return rab;
    }

    public void setRab(List<RabDTO> rab) {
        this.rab = rab;
    }

    public List<ScopeDTO> getScopesPks() {
        return scopesPks;
    }

    public void setScopesPks(List<ScopeDTO> scopesPks) {
        this.scopesPks = scopesPks;
    }

    public List<AttachmentDTO> getAttachmentsPks() {
        return attachmentsPks;
    }

    public void setAttachmentsPks(List<AttachmentDTO> attachmentsPks) {
        this.attachmentsPks = attachmentsPks;
    }
}
