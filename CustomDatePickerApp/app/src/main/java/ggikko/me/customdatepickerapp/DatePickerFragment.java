package ggikko.me.customdatepickerapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ggikko on 2016. 11. 14..
 */

public class DatePickerFragment extends DialogFragment {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        long time = new Date().getTime();
        long test = new Date().getTime() - 345600;
        c.add(Calendar.DATE,4);
//        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        //        datePickerDialog.getDatePicker().setMaxDate(time);


        datePickerDialog.getDatePicker().setMinDate(time);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());


        return datePickerDialog;
    }


}