package com.femmehacks.charityconnect.storage;

import java.util.Date;

public class EventPOJO {

    private String title;
    private Date date;
    private DriveType driveType;
    private boolean hasPickup;
    private String location;

    public EventPOJO() {}

    public EventPOJO(String title, Date date, DriveType driveType, boolean hasPickup, String location) {
        this.title = title;
        this.date = date;
        this.driveType = driveType;
        this.hasPickup = hasPickup;
        this.location = location;
    }

    public EventPOJO(EventPOJO e) {
        this.title = e.title;
        this.date = e.date;
        this.driveType = e.driveType;
        this.hasPickup = e.hasPickup;
        this.location = e.location;
    }

    public String getLocation() {return location;}
    public String setLocation(String location) {return this.location = location;}
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


    @Override
    public String toString() {
        return "EventPOJO{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", driveType=" + driveType +
                ", hasPickup=" + hasPickup +
                ", location=" + location +
                '}';
    }
}
