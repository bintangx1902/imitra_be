package com.postgre.springapipostgre.models.enums;

public enum BaseChoices {
    MOU("m", "MoU"),
    NDA("n", "NDA");

    private final String code;
    private final String displayName;

    BaseChoices(String code, String displayName) {
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
