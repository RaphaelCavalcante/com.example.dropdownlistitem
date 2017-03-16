package com.home.rhounsell.example.dropdownlistitem.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.home.rhounsell.example.dropdownlistitem.database.SQLiteHelper;
import com.home.rhounsell.example.dropdownlistitem.database.DatabaseContract.VaccinationYearEntry;
import com.home.rhounsell.example.dropdownlistitem.model.VaccinationYear;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class VaccinationYearDataSource {
    private SQLiteHelper helper;
    private SQLiteDatabase database;
    private String [] allColumns = {
            VaccinationYearEntry._ID,
            VaccinationYearEntry.COLUMN_NAME_YEAR,
            VaccinationYearEntry.COLUMN_NAME_DONE
    };

    public VaccinationYearDataSource(Context context){
        helper = SQLiteHelper.getInstance(context);
    }
    public void open(){
        database = helper.getWritableDatabase();
        if(!database.isReadOnly()){
            database.execSQL("PRAGMA foreign_keys =ON;");
        }
    }
    public void close (){helper.close();}

    public VaccinationYear createVaccinationYear(long year){
        ContentValues values = new ContentValues();
        values.put(VaccinationYearEntry.COLUMN_NAME_YEAR, year);
        values.put(VaccinationYearEntry.COLUMN_NAME_DONE, false);

        long insertId = database.insert(VaccinationYearEntry.TABLE_NAME, null, values);
        Cursor cursor = database.query(VaccinationYearEntry.TABLE_NAME, allColumns,
                VaccinationYearEntry._ID+"="+insertId, null, null, null,null);
        cursor.moveToFirst();
        VaccinationYear vaccinationYear = cursorToCalendar(cursor);
        cursor.close();
        return vaccinationYear;
    }
    public List<VaccinationYear> getListVaccinationYear(){
        String allQuery = "SELECT * FROM "+VaccinationYearEntry.TABLE_NAME;
        List <VaccinationYear> yearList = new ArrayList<>();
        Cursor cursor = database.rawQuery(allQuery, null);
        cursor.moveToFirst();
        do{
            VaccinationYear newYear = new VaccinationYear();
            newYear.setId(cursor.getLong(0));
            newYear.setYear(cursor.getLong(1));
            newYear.setDone(cursor.getString(2));

            yearList.add(newYear);
        }while(cursor.moveToNext());
        return yearList;
    }
    public int countTable(){
        String countQuery = "SELECT count(*) FROM "+VaccinationYearEntry.TABLE_NAME;
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int iCount = cursor.getInt(0);
        return iCount;
    }
    public VaccinationYear getVaccinationYearById(long calendarId){
        Cursor cursor = database.query(VaccinationYearEntry.TABLE_NAME, allColumns,
                VaccinationYearEntry._ID+"="+calendarId,null,null,null,null);
        return cursorToCalendar(cursor);
    }
    private VaccinationYear cursorToCalendar(Cursor cursor){
        VaccinationYear vaccinationYear = new VaccinationYear();
        vaccinationYear.setId(cursor.getLong(0));
        vaccinationYear.setYear(cursor.getLong(1));
        vaccinationYear.setDone(cursor.getString(2));
        return vaccinationYear;
    }
}
