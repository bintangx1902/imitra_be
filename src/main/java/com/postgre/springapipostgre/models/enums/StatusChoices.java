package com.postgre.springapipostgre.models.enums;

public enum StatusChoices {
    P("p", "pengajuan"),
    R("r", "revisi"),
    T("t", "ditolak"),
    M("m", "manajer"),
    V("v", "vp"),
    D1("d1", "direksi"),
    MK("mk", "manager_kemitraan"),
    SK("sk", "staff_kemitraan"),
    F("f", "Selesai");

    private final String code;
    private final String displayName;

    StatusChoices(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
