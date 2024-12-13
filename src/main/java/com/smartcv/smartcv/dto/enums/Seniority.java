package com.smartcv.smartcv.dto.enums;

public enum Seniority {

    INTERN("Intern"),
    JUNIOR("Junior"),
    MID_LEVEL("Mid-Level"),
    SENIOR("Senior");

    private final String displayName;


    Seniority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "Seniority{" +
                "displayName='" + displayName + '\'' +
                '}';
    }
}
