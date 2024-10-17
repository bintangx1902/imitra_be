package com.postgre.springapipostgre.models;

import jakarta.persistence.*;

@Entity
@Table(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String file;

    @ManyToOne(optional = true)
    @JoinColumn(name = "mou_nda_id", nullable = true)
    private MouNda mouNda;

    @ManyToOne(optional = true)
    @JoinColumn(name = "pks_id", nullable = true)
    private PKS pks;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public MouNda getMouNda() {
        return mouNda;
    }

    public void setMouNda(MouNda mouNda) {
        this.mouNda = mouNda;
    }

    public PKS getPks() {
        return pks;
    }

    public void setPks(PKS pks) {
        this.pks = pks;
    }

    @Override
    public String toString() {
        return file != null ? file.substring(file.lastIndexOf("/") + 1) : "No file";
    }
}
