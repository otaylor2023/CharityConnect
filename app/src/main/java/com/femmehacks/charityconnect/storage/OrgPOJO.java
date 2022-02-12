package com.femmehacks.charityconnect.storage;

import java.util.Date;

public class OrgPOJO {

    private String name;
    private String phoneNumber;
    private String email;
    private String website;
    private String socialMedia;

    public OrgPOJO() {}

    public OrgPOJO(String name, String phoneNumber, String email, String website, String socialMedia) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.socialMedia = socialMedia;
    }

    public OrgPOJO(OrgPOJO e) {
        this.name = e.name;
        this.phoneNumber = e.phoneNumber;
        this.email = e.email;
        this.website = e.website;
        this.socialMedia = e.socialMedia;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) { this.website = website; }

    public String getSocialMedia() { return socialMedia; }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }


    @Override
    public String toString() {
        return "OrgPOJO{" +
                "name='" + name + '\'' +
                ", phone number=" + phoneNumber +
                ", email=" + email +
                ", website=" + website +
                ", social media=" + socialMedia +
                '}';
    }
}
