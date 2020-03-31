package dev.kcrm.web.data.documents;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "crm.client")
public class Client extends Contact {

    private String website;
    private String company;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
