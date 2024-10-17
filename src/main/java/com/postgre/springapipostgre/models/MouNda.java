package com.postgre.springapipostgre.models;

import com.postgre.springapipostgre.models.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "moundas")
public class MouNda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BaseChoices base;

    private LocalDateTime submissionDate = LocalDateTime.now();
    private String partnershipTitle;
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
    private String mouNdaNumber;
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

    public BaseChoices getBase() {
        return base;
    }

    public void setBase(BaseChoices base) {
        this.base = base;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
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

    public Functionary getOfficialUndersign() {
        return officialUndersign;
    }

    public void setOfficialUndersign(Functionary officialUndersign) {
        this.officialUndersign = officialUndersign;
    }

    @Override
    public String toString() {
        return "permintaan " + base + ", dengan id : " + id;
    }
}
