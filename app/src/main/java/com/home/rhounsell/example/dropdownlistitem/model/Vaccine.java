package com.home.rhounsell.example.dropdownlistitem.model;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class Vaccine {
    private long id;
    private String vaccineName;
    private String vaccineDate;
    private String vaccineApplied;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(String vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public String getVaccineApplied() {
        return vaccineApplied;
    }

    public void setVaccineApplied(String vaccineApplied) {
        this.vaccineApplied = vaccineApplied;
    }
}
