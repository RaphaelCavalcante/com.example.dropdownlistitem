package com.home.rhounsell.example.dropdownlistitem.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.home.rhounsell.example.dropdownlistitem.database.DatabaseContract.VaccineEntry;
import com.home.rhounsell.example.dropdownlistitem.database.SQLiteHelper;
import com.home.rhounsell.example.dropdownlistitem.model.Vaccine;

import java.util.List;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class VaccineDataSource {
    private SQLiteHelper helper;
    private SQLiteDatabase database;
    private String [] allColumns={
            VaccineEntry._ID,
            VaccineEntry.COLUMN_NAME_NAME,
            VaccineEntry.COLUMN_NAME_DATE,
            VaccineEntry.COLUMN_NAME_APPLIED
    };

    public VaccineDataSource(Context context){
        helper = SQLiteHelper.getInstance(context);
    }

    public void open(){
        database = helper.getWritableDatabase();
        if(!database.isReadOnly()){
            database.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public void close(){
        helper.close();
    }

    public Vaccine createVaccine(Vaccine newVaccine){
        ContentValues values = new ContentValues();
        values.put(VaccineEntry.COLUMN_NAME_NAME, newVaccine.getVaccineName());
        values.put(VaccineEntry.COLUMN_NAME_DATE, newVaccine.getVaccineDate());
        values.put(VaccineEntry.COLUMN_NAME_APPLIED, newVaccine.getVaccineApplied());

        long insertId = database.insert(VaccineEntry.TABLE_NAME, null, values);
        Cursor cursor = database.query(VaccineEntry.TABLE_NAME, allColumns,
                VaccineEntry._ID+"="+insertId, null,null,null,null);

        cursor.moveToFirst();
        Vaccine vaccine = cursorToVaccine(cursor);
        cursor.close();
        return vaccine;
    }
    public void updateMultipleVaccines(List<Vaccine> vaccineList){
        //update vaccine set vaccine.applied=applied where vaccine.id = vaccine.getId
        for(Vaccine vaccine : vaccineList){
            long bool=!vaccine.getVaccineApplied().isEmpty()?1:0;
            String query = "update "+VaccineEntry.TABLE_NAME+" set "+VaccineEntry.COLUMN_NAME_APPLIED
                    +"="+
                    bool+
                    " where "+VaccineEntry._ID+
                    "="+vaccine.getId();
            database.execSQL(query);
        }
    }
    private Vaccine cursorToVaccine(Cursor cursor){
        Vaccine vaccine = new Vaccine();
        vaccine.setId(cursor.getInt(0));
        vaccine.setVaccineName(cursor.getString(1));
        vaccine.setVaccineApplied(cursor.getString(2));
        vaccine.setVaccineDate(cursor.getString(3));
        return vaccine;
    }
}
