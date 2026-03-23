package com.example.monappliimmo;

import java.io.Serializable;

public class Intervention implements Serializable {
    private long id;
    private String type;
    private String description;
    private String datePrevue;
    private String dateRealise;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDatePrevue() { return datePrevue; }
    public void setDatePrevue(String datePrevue) { this.datePrevue = datePrevue; }
    public String getDateRealise() { return dateRealise; }
    public void setDateRealise(String dateRealise) { this.dateRealise = dateRealise; }
}