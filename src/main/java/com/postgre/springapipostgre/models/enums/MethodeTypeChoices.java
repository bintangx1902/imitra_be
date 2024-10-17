package com.postgre.springapipostgre.models.enums;

public enum MethodeTypeChoices {
    T1("t1", "Tunjuk Langsung"),
    T2("t2", "Terbuka");

    private final String code;
    private final String displayName;

    MethodeTypeChoices(String code, String displayName) {
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
