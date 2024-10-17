package com.postgre.springapipostgre.models.enums;

public enum MaterialTypeChoices {
    M("m", "Material Only"),
    S("s", "Service Only"),
    MS("ms", "Material and Service");

    private final String code;
    private final String displayName;

    MaterialTypeChoices(String code, String displayName) {
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
