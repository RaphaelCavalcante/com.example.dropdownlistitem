package com.home.rhounsell.example.dropdownlistitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.home.rhounsell.example.dropdownlistitem.adapters.ExpandableListAdapter;
import com.home.rhounsell.example.dropdownlistitem.database.DatabaseCreateStrings;
import com.home.rhounsell.example.dropdownlistitem.datasource.VaccinationYearDataSource;
import com.home.rhounsell.example.dropdownlistitem.datasource.VaccinationYearVaccineDataSource;
import com.home.rhounsell.example.dropdownlistitem.datasource.VaccineDataSource;
import com.home.rhounsell.example.dropdownlistitem.dialog.FirstTimerConfirmationDialog;
import com.home.rhounsell.example.dropdownlistitem.dialog.FirstVaccineDateDialog;
import com.home.rhounsell.example.dropdownlistitem.model.VaccinationYear;
import com.home.rhounsell.example.dropdownlistitem.model.Vaccine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IVaccinationRoutine{
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<Long, List<Vaccine>> listDataChild;

    private VaccinationYearVaccineDataSource vaccinationYearVaccineDataSource;
    private VaccinationYearDataSource vaccinationYearDataSource;
    private VaccineDataSource vaccineDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<Long , List<Vaccine>>();

        expListView = (ExpandableListView) findViewById(R.id.lvl_exp);

        vaccinationYearVaccineDataSource = new VaccinationYearVaccineDataSource(this);
        vaccinationYearDataSource = new VaccinationYearDataSource(this);
        vaccineDataSource = new VaccineDataSource(this);

        vaccinationYearVaccineDataSource.open();
        vaccinationYearDataSource.open();
        vaccineDataSource.open();

        if(vaccinationYearDataSource.countTable() <= 0){
            FirstTimerConfirmationDialog.getInstance(this).show(getSupportFragmentManager(),"first_vaccine");
        }
    }
    private  void prepareListData(){

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

       /* listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);*/
    }

    @Override
    public void onFirstVaccination() {
        FirstVaccineDateDialog.getInstance(this).show(getSupportFragmentManager(),"vaccination_date");
    }

    @Override
    public void createFirstTimeVaccines(String date) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            cal.setTime(sdf.parse(date));

            VaccinationYear year=vaccinationYearDataSource.createVaccinationYear(cal.get(Calendar.YEAR));

            List<Calendar> vaccineDates = getDates(cal);

            Vaccine v8= vaccineDataSource.createVaccine(setVaccine(VaccineNames.V8.name(),
                    vaccineDates.get(0).get(Calendar.DAY_OF_MONTH),
                    vaccineDates.get(0).get(Calendar.MONTH),
                    vaccineDates.get(0).get(Calendar.YEAR),false));
            Vaccine pneumonia= vaccineDataSource.createVaccine(setVaccine(VaccineNames.PNEUMONIA.name(),
                    vaccineDates.get(0).get(Calendar.DAY_OF_MONTH),
                    vaccineDates.get(0).get(Calendar.MONTH),
                    vaccineDates.get(0).get(Calendar.YEAR),false));

            vaccinationYearVaccineDataSource.createYearVaccine(year.getId(),v8.getId());
            vaccinationYearVaccineDataSource.createYearVaccine(year.getId(),pneumonia.getId());

            v8.setVaccineDate(vaccineDates.get(1).get(Calendar.DAY_OF_MONTH)+"-"+
                    vaccineDates.get(1).get(Calendar.MONTH)+"-"+
                    vaccineDates.get(1).get(Calendar.YEAR));

            Vaccine raiva= vaccineDataSource.createVaccine(setVaccine(VaccineNames.RAIVA.name(),
                    vaccineDates.get(1).get(Calendar.DAY_OF_MONTH),
                    vaccineDates.get(1).get(Calendar.MONTH),
                    vaccineDates.get(1).get(Calendar.YEAR),false));
            vaccinationYearVaccineDataSource.createYearVaccine(year.getId(),vaccineDataSource.createVaccine(v8).getId());
            vaccinationYearVaccineDataSource.createYearVaccine(year.getId(),raiva.getId());

            v8.setVaccineDate(vaccineDates.get(2).get(Calendar.DAY_OF_MONTH)+"-"+
                    vaccineDates.get(2).get(Calendar.MONTH)+"-"+
                    vaccineDates.get(2).get(Calendar.YEAR));

            Vaccine giardia= vaccineDataSource.createVaccine(setVaccine(VaccineNames.GIARDIA.name(),
                    vaccineDates.get(2).get(Calendar.DAY_OF_MONTH),
                    vaccineDates.get(2).get(Calendar.MONTH),
                    vaccineDates.get(2).get(Calendar.YEAR),false));

            vaccinationYearVaccineDataSource.createYearVaccine(year.getId(),vaccineDataSource.createVaccine(v8).getId());
            vaccinationYearVaccineDataSource.createYearVaccine(year.getId(),giardia.getId());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        populateView();
    }
    private List<Calendar> getDates(Calendar startDate){
        List<Calendar> vaccineDates = new ArrayList<>();
        vaccineDates.add(startDate);
        for(int i =1;i<3;i++){
            Calendar aux = Calendar.getInstance();
            aux.setTimeInMillis(vaccineDates.get(i-1).getTimeInMillis());
            aux.add(Calendar.DAY_OF_MONTH, 45);
            vaccineDates.add(aux);
        }
        return vaccineDates;
    }
    private Vaccine setVaccine(String vaccineName, int day, int month, int year, boolean isApplied){
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName(vaccineName);
        vaccine.setVaccineDate(day+"-"+month+"-"+year);
        vaccine.setVaccineApplied(isApplied+"");
        Log.i("TESTE", vaccine.getVaccineName()+" "+vaccine.getVaccineDate()+" "+vaccine.getVaccineApplied());
        return vaccine;
    }
    private void populateView(){
        List<VaccinationYear> allYears = vaccinationYearDataSource.getListVaccinationYear();
        List<Vaccine>vaccines = vaccinationYearVaccineDataSource.getAllVaccinesFromVaccinationYear(allYears.get(0).getId());

        listDataChild.put(allYears.get(0).getYear() , vaccines);
        listAdapter = new ExpandableListAdapter(this, allYears, listDataChild);
        expListView.setAdapter(listAdapter);
    }
}
