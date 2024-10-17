package com.postgre.springapipostgre.models.enums;

public enum BudgetTypeChoices {
    O("o", "Opex"),
    C("c", "Capex"),
    OC("oc", "Opex & Capex");

    private final String code;
    private final String displayName;

    BudgetTypeChoices(String code, String displayName) {
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
