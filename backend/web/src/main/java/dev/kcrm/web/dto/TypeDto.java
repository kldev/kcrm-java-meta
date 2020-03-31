package dev.kcrm.web.dto;

import dev.kcrm.web.data.documents.LeadStatus;

public class TypeDto {

    private String code;
    private String description;

    public TypeDto(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static TypeDto FromLeadStatus(LeadStatus leadStatus) {

        return new TypeDto(leadStatus.getCode(), leadStatus.getDescription());
    }
}
