package com.postgre.springapipostgre.models;

import jakarta.persistence.*;

@Entity
@Table(name = "scopes")
public class Scope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scopeName;

    @ManyToOne(optional = true)
    @JoinColumn(name = "mounda_id", nullable = true)
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

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
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
        return scopeName;
    }
}
