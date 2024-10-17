package com.postgre.springapipostgre.models.enums;

public enum TypeChoices {
    JO("JO", "Join Operation"),
    JI("JI", "Join Investment"),
    R("R", "Reseller"),
    B("B", "Bundling Layanan");

    private final String code;
    private final String displayName;

    TypeChoices(String code, String displayName) {
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
