package com.femmehacks.charityconnect.storage;

import java.util.Date;

public class EventPOJO {

    private String title;
    private Date date;
    private DriveType driveType;
    private boolean hasPickup;
    private boolean hasDropoff;

    public EventPOJO() {}

    public EventPOJO(String title, Date date, DriveType driveType, boolean hasPickup, boolean hasDropoff) {
        this.title = title;
        this.date = date;
        this.driveType = driveType;
        this.hasPickup = hasPickup;
        this.hasDropoff = hasDropoff;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DriveType getDriveType() {
        return driveType;
    }

    public void setDriveType(DriveType driveType) {
        this.driveType = driveType;
    }

    public boolean hasPickup() {
        return hasPickup;
    }

    public void setHasPickup(boolean hasPickup) {
        this.hasPickup = hasPickup;
    }

    public boolean hasDropoff() {
        return hasDropoff;
    }

    public void setHasDropoff(boolean hasDropoff) {
        this.hasDropoff = hasDropoff;
    }
}
