package com.home.rhounsell.example.dropdownlistitem.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.home.rhounsell.example.dropdownlistitem.database.SQLiteHelper;
import com.home.rhounsell.example.dropdownlistitem.model.VaccinationYearVaccine;

import com.home.rhounsell.example.dropdownlistitem.database.DatabaseContract.VaccinationYearVaccineEntry;
import com.home.rhounsell.example.dropdownlistitem.database.DatabaseContract.VaccineEntry;
import com.home.rhounsell.example.dropdownlistitem.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class VaccinationYearVaccineDataSource {
    private SQLiteHelper helper;
    private SQLiteDatabase database;
    private String[] allColumns={
            VaccinationYearVaccineEntry._ID,
            VaccinationYearVaccineEntry.COLUMN_NAME_VACCINATION_YEAR_ID,
            VaccinationYearVaccineEntry.COLUMN_NAME_VACCINE_ID
    };

    public VaccinationYearVaccineDataSource(Context context){
        helper = SQLiteHelper.getInstance(context);
    }
    public void open() throws SQLException {
        database = helper.getWritableDatabase();
        if(!database.isReadOnly()){
            database.execSQL("PRAGMA foreign_keys =ON");
        }
    }
    public void close(){
        helper.close();
    }
    public VaccinationYearVaccine createYearVaccine(long vaccinationYearId, long vaccineId){
        ContentValues values = new ContentValues();
        values.put(VaccinationYearVaccineEntry.COLUMN_NAME_VACCINATION_YEAR_ID, vaccinationYearId);
        values.put(VaccinationYearVaccineEntry.COLUMN_NAME_VACCINE_ID, vaccineId);

        long insertId = database.insert(VaccinationYearVaccineEntry.TABLE_NAME,null,values);
        Cursor cursor = database.query(VaccinationYearVaccineEntry.TABLE_NAME, allColumns,
                VaccinationYearVaccineEntry._ID+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        VaccinationYearVaccine vaccinationYearVaccine = cursorToVaccinationYearVaccine(cursor);
        cursor.close();
        return vaccinationYearVaccine;
    }
    public List<Vaccine> getAllVaccinesFromVaccinationYear(long vaccinationYearId){
        String query = "select * from "+
                VaccineEntry.TABLE_NAME +
                " inner join "+
                VaccinationYearVaccineEntry.TABLE_NAME+
                " on "+
                VaccineEntry.TABLE_NAME+"."+
                VaccineEntry._ID+"="+
                VaccinationYearVaccineEntry.TABLE_NAME+"."+
                VaccinationYearVaccineEntry.COLUMN_NAME_VACCINE_ID+
                " where "+
                VaccinationYearVaccineEntry.COLUMN_NAME_VACCINATION_YEAR_ID+"="+vaccinationYearId;
        List<Vaccine> vaccineList = new ArrayList<Vaccine>();
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Vaccine vaccine = new Vaccine();
                vaccine.setId(cursor.getLong(0));
                vaccine.setVaccineName(cursor.getString(1));
                vaccine.setVaccineDate(cursor.getString(2));
                vaccine.setVaccineApplied(cursor.getString(3));

                vaccineList.add(vaccine);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return vaccineList;
    }
    public int deletePetVaccine(long vaccinationYearId, long vaccineId){
        return database.delete(VaccinationYearVaccineEntry.TABLE_NAME,
                VaccinationYearVaccineEntry.COLUMN_NAME_VACCINATION_YEAR_ID+"="+vaccinationYearId+
                        " AND "
                        + VaccinationYearVaccineEntry.COLUMN_NAME_VACCINE_ID+"="+vaccineId,null);
    }
    private VaccinationYearVaccine cursorToVaccinationYearVaccine(Cursor cursor) {
        VaccinationYearVaccine vaccinationYearVaccine = new VaccinationYearVaccine();
        vaccinationYearVaccine.setId(cursor.getLong(0));
        vaccinationYearVaccine.setYearId(cursor.getLong(1));
        vaccinationYearVaccine.setVaccineId(cursor.getLong(2));

        return vaccinationYearVaccine;
    }
}
