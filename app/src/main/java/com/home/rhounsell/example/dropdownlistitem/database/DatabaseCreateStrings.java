package com.home.rhounsell.example.dropdownlistitem.database;

import com.home.rhounsell.example.dropdownlistitem.database.DatabaseContract.VaccinationYearEntry;
import com.home.rhounsell.example.dropdownlistitem.database.DatabaseContract.VaccineEntry;
import com.home.rhounsell.example.dropdownlistitem.database.DatabaseContract.VaccinationYearVaccineEntry;


/**
 * Created by RHounsell on 14/03/2017.
 */

public enum DatabaseCreateStrings {
    VACCINATION_YEAR("create table "+VaccinationYearEntry.TABLE_NAME
            +"("+ VaccinationYearEntry._ID+" integer primary key autoincrement, "
            +VaccinationYearEntry.COLUMN_NAME_YEAR+" integer not null, "
            +VaccinationYearEntry.COLUMN_NAME_DONE+" text not null);"),
    VACCINE("create table "+VaccineEntry.TABLE_NAME+" ("
            +VaccineEntry._ID+" integer primary key autoincrement, "
            +VaccineEntry.COLUMN_NAME_NAME+" text not null, "
            +VaccineEntry.COLUMN_NAME_DATE+" text not null, "
            +VaccineEntry.COLUMN_NAME_APPLIED+" text not null);"),
    VACCINATION_YEAR_VACCINE("create table "+VaccinationYearVaccineEntry.TABLE_NAME+" ("
            + VaccinationYearVaccineEntry._ID+" integer primary key autoincrement, "
            +VaccinationYearVaccineEntry.COLUMN_NAME_VACCINATION_YEAR_ID+" integer not null, "
            +VaccinationYearVaccineEntry.COLUMN_NAME_VACCINE_ID+" integer not null);");
    private String create;
    DatabaseCreateStrings(String create){
        this.create = create;
    }
    public String create(){
        return create;
    }
}
