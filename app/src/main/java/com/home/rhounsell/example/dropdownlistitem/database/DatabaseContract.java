package com.home.rhounsell.example.dropdownlistitem.database;

import android.provider.BaseColumns;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class DatabaseContract {
    private DatabaseContract(){}

    public static class VaccinationYearEntry implements BaseColumns {
        public static final String TABLE_NAME = "vaccination_year";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_DONE ="done";
    }
    public static class VaccineEntry implements BaseColumns{
        public static final String TABLE_NAME="vaccine";
        public static final String COLUMN_NAME_NAME="vaccine_name";
        public static final String COLUMN_NAME_DATE="vaccine_date";
        public static final String COLUMN_NAME_APPLIED="applied";
    }
    public static class VaccinationYearVaccineEntry implements BaseColumns{
        public static final String TABLE_NAME="vaccine_year_vaccine";
        public static final String COLUMN_NAME_VACCINATION_YEAR_ID="year_id";
        public static final String COLUMN_NAME_VACCINE_ID="vaccine_id";
    }
}
