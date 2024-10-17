package com.postgre.springapipostgre.models;

import com.postgre.springapipostgre.models.enums.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pkss")
public class PKS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeChoices partnershipType;

    private LocalDateTime submissionDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private BudgetTypeChoices budgetType;

    private String budgetNumber;

    @Enumerated(EnumType.STRING)
    private MethodeTypeChoices partnershipMethod;

    @Enumerated(EnumType.STRING)
    private MaterialTypeChoices materialType;

    private String title;
    private String background;
    private String note;
    private String partnershipCandidate;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private StatusChoices status;

    private int positionLevel = 1;

    private boolean isStopClock = false;
    private String responseText;
    private String approvalNote;
    private String pksNumber;
    private LocalDateTime approvalCompletionDate;

    @ManyToOne
    private Functionary officialUndersign;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeChoices getPartnershipType() {
        return partnershipType;
    }

    public void setPartnershipType(TypeChoices partnershipType) {
        this.partnershipType = partnershipType;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public BudgetTypeChoices getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(BudgetTypeChoices budgetType) {
        this.budgetType = budgetType;
    }

    public String getBudgetNumber() {
        return budgetNumber;
    }

    public void setBudgetNumber(String budgetNumber) {
        this.budgetNumber = budgetNumber;
    }

    public MethodeTypeChoices getPartnershipMethod() {
        return partnershipMethod;
    }

    public void setPartnershipMethod(MethodeTypeChoices partnershipMethod) {
        this.partnershipMethod = partnershipMethod;
    }

    public MaterialTypeChoices getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialTypeChoices materialType) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusChoices getStatus() {
        return status;
    }

    public void setStatus(StatusChoices status) {
        this.status = status;
    }

    public int getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(int positionLevel) {
        this.positionLevel = positionLevel;
    }

    public boolean isStopClock() {
        return isStopClock;
    }

    public void setStopClock(boolean stopClock) {
        isStopClock = stopClock;
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

    public String getPksNumber() {
        return pksNumber;
    }

    public void setPksNumber(String pksNumber) {
        this.pksNumber = pksNumber;
    }

    public LocalDateTime getApprovalCompletionDate() {
        return approvalCompletionDate;
    }

    public void setApprovalCompletionDate(LocalDateTime approvalCompletionDate) {
        this.approvalCompletionDate = approvalCompletionDate;
    }

    public Functionary getOfficialUndersign() {
        return officialUndersign;
    }

    public void setOfficialUndersign(Functionary officialUndersign) {
        this.officialUndersign = officialUndersign;
    }

    @Override
    public String toString() {
        return "permintaan PKS dengan id : " + id;
    }
}
