package dev.kcrm.web.data.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "crm.lead.type")
public class LeadStatus {

    @Id
    private String id;

    @NotNull
    private String code;

    @NotNull
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public static final class LeadStatusBuilder {
        private String id;
        private String code;
        private String description;

        private LeadStatusBuilder() {
        }

        public static LeadStatusBuilder aLeadStatus() {
            return new LeadStatusBuilder();
        }

        public LeadStatusBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public LeadStatusBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public LeadStatusBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public LeadStatus build() {
            LeadStatus leadStatus = new LeadStatus();
            leadStatus.setId(id);
            leadStatus.setCode(code);
            leadStatus.setDescription(description);
            return leadStatus;
        }
    }
}
