package com.home.rhounsell.example.dropdownlistitem.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by RHounsell on 15/03/2017.
 */

public class DatePickerDialogFragment extends DialogFragment {
    private static DatePickerDialog.OnDateSetListener changeDateListener;
    public static DatePickerDialogFragment getInstance(DatePickerDialog.OnDateSetListener listener){
        DatePickerDialogFragment fragment= new DatePickerDialogFragment();
        changeDateListener= listener;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog dialog;
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        dialog = new DatePickerDialog(getActivity(), changeDateListener, year, month, dayOfMonth);
        return dialog;
    }
}
