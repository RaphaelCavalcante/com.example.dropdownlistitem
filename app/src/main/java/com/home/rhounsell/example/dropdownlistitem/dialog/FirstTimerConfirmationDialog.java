package com.home.rhounsell.example.dropdownlistitem.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.home.rhounsell.example.dropdownlistitem.IVaccinationRoutine;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class FirstTimerConfirmationDialog extends DialogFragment {
    private static IVaccinationRoutine vaccinationRoutine;
    public static FirstTimerConfirmationDialog getInstance(IVaccinationRoutine routine){
        FirstTimerConfirmationDialog fragment = new FirstTimerConfirmationDialog();
        vaccinationRoutine = routine;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("First Vaccination?").setPositiveButton("Yes",
                this.onPositiveButtonCLick()).setNegativeButton("No", null);
        return builder.create();
    }

    private DialogInterface.OnClickListener onPositiveButtonCLick(){
        return new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                vaccinationRoutine.onFirstVaccination();
            }
        };
    }
}
