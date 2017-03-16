package com.home.rhounsell.example.dropdownlistitem.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.home.rhounsell.example.dropdownlistitem.IVaccinationRoutine;
import com.home.rhounsell.example.dropdownlistitem.R;

/**
 * Created by RHounsell on 15/03/2017.
 */

public class FirstVaccineDateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private EditText vaccineDate;
    private DatePickerDialog.OnDateSetListener listener;
    private static IVaccinationRoutine createVaccinesTrigger;
    public static FirstVaccineDateDialog getInstance(IVaccinationRoutine trigger){
        FirstVaccineDateDialog dialog = new FirstVaccineDateDialog();
        createVaccinesTrigger = trigger;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_vaccine_date_layout,null);
        AlertDialog.Builder builder = new AlertDialog.Builder (getActivity());
        vaccineDate = (EditText) dialogView.findViewById(R.id.vaccine_date);
        vaccineDate.setOnClickListener(this.showDateDialog());
        listener = this;
        builder.setTitle("Set Vaccine Date").setView(dialogView).setPositiveButton("Set", this.onSetVaccineDate()).
                setNegativeButton("Cancel", null);
        return builder.create();
    }
    private DialogInterface.OnClickListener onSetVaccineDate(){
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                createVaccinesTrigger.createFirstTimeVaccines(vaccineDate.getText().toString());
            }
        };
    }
    private View.OnClickListener showDateDialog(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogFragment.getInstance(listener).show(getFragmentManager(),
                        "vaccine_date");
            }
        };
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth2) {
        month=month+1;
        vaccineDate.setText(dayOfMonth2+"-"+month+"-"+year);
    }
}
