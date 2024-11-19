package com.smartcv.smartcv.dto.enums;

public enum Profession {

    SOFTWARE_DEVELOPER("Software Developer"),
    SOFTWARE_ENGINEER("Software Engineer"),
    DATA_ANALYST("Data Analyst"),
    DATA_SCIENTIST("Data Scientist"),
    DATA_ENGINEER("Data Engineer"),
    SOLUTION_ARCHITECT("Solution Architect"),
    SYSTEM_ADMINISTRATOR("System Administrator"),
    NETWORK_ENGINEER("Network Engineer"),
    IT_CONSULTANT("IT Consultant"),
    TECH_SUPPORT("Tech Support"),
    SECURITY_ANALYST("Security Analyst"),
    PROJECT_MANAGER("Project Manager"),
    GRAPHIC_DESIGNER("Graphic Designer"),
    UX_UI_DESIGNER("UX/UI Designer"),
    COPYWRITER("Copywriter"),
    JOURNALIST("Journalist"),
    TEACHER("Teacher"),
    DOCTOR("Doctor"),
    NURSE("Nurse"),
    PSYCHOLOGIST("Psychologist"),
    PHYSIOTHERAPIST("Physiotherapist"),
    PHARMACIST("Pharmacist"),
    LAWYER("Lawyer"),
    ACCOUNTANT("Accountant"),
    CIVIL_ENGINEER("Civil Engineer"),
    ELECTRICAL_ENGINEER("Electrical Engineer"),
    MECHANICAL_ENGINEER("Mechanical Engineer"),
    ARCHITECT("Architect"),
    ADVERTISER("Advertiser"),
    ADMINISTRATOR("Administrator"),
    ECONOMIST("Economist"),
    IT_TECHNICIAN("IT Technician"),
    PRODUCTION_OPERATOR("Production Operator"),
    LOGISTICS_TECHNICIAN("Logistics Technician"),
    OCCUPATIONAL_SAFETY_TECHNICIAN("Occupational Safety Technician"),
    DRIVER("Driver"),
    SALES_PERSON("Sales Person"),
    SALES_REPRESENTATIVE("Sales Representative"),
    CUSTOMER_SERVICE_AGENT("Customer Service Agent"),
    ADMINISTRATIVE_ASSISTANT("Administrative Assistant");

    private final String displayName;

    // Construtor
    Profession(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}