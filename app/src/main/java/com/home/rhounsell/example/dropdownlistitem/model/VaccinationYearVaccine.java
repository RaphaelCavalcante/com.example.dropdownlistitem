package com.home.rhounsell.example.dropdownlistitem.model;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class VaccinationYearVaccine {
    private long id;
    private long yearId;
    private long vaccineId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getYearId() {
        return yearId;
    }

    public void setYearId(long yearId) {
        this.yearId = yearId;
    }

    public long getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(long vaccineId) {
        this.vaccineId = vaccineId;
    }
}
