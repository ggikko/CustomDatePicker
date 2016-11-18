package ggikko.me.customdatepickerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTestPicker extends AppCompatActivity {

    @BindView(R.id.yearNumberPicker)
    NumberPicker yearNumberPicker;
    @BindView(R.id.monthNumberPicker)
    NumberPicker monthNumberPicker;
    @BindView(R.id.dayNumberPicker)
    NumberPicker dayNumberPicker;

    String[] lastYearArray;
    String[] lastDayArray;

    String[] lastMonthArray;
    String[] monthArray;
    String[] currentMonthArray;

    String[] dayArray;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_test_picker);

        ButterKnife.bind(this);

        calendar = Calendar.getInstance();

        int currentYear = calendar.get(calendar.YEAR);
        int currentMonth = calendar.get(calendar.MONTH) + 1;
        int currentDay = calendar.get(calendar.DAY_OF_MONTH);

        calendar.add(calendar.YEAR, 4);

        final int yearAfterThree = calendar.get(calendar.YEAR);
        final int monthAfterThree = calendar.get(calendar.MONTH) + 1;
        int dayAfterThree = calendar.get(calendar.DAY_OF_MONTH);

        monthArray = makeMonthArray();

        currentMonthArray = makeCurrentMonthArray(currentMonth);

        lastYearArray = makeLastYearArray(currentYear, yearAfterThree);

        lastMonthArray = makeLastMonthArray(monthAfterThree);

        lastDayArray = makeLastDayArray(currentDay, dayAfterThree);

        lastYearArray = makeLastYearArray(currentYear, yearAfterThree);


        yearNumberPicker.setMinValue(0);
        yearNumberPicker.setMaxValue(lastYearArray.length - 1);
        yearNumberPicker.setDisplayedValues(lastYearArray);
        yearNumberPicker.setValue(0);

        Log.e("ggikko", "? : " + monthArray.length);
        monthNumberPicker.setMinValue(0);
        monthNumberPicker.setMaxValue(currentMonthArray.length - 1);
        monthNumberPicker.setDisplayedValues(currentMonthArray);


//        monthNumberPicker.setDisplayedValues(monthArray);

        yearNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal == 0) {
                    monthNumberPicker.setMinValue(0);
                    monthNumberPicker.setMaxValue(currentMonthArray.length - 1);
                    monthNumberPicker.setDisplayedValues(currentMonthArray);

                } else if (newVal == 3) {
                    monthNumberPicker.setMinValue(0);
                    monthNumberPicker.setMaxValue(lastMonthArray.length - 1);
                    monthNumberPicker.setDisplayedValues(lastMonthArray);
//                    dayNumberPicker.setDisplayedValues(lastDayArray);

                } else {
                    monthNumberPicker.setMinValue(0);
                    monthNumberPicker.setMaxValue(monthArray.length - 1);
//                    monthNumberPicker.setDisplayedValues(monthArray);
//                    dayNumberPicker.setDisplayedValues();
                }
            }
        });
    }

    private String[] makeMonthArray() {
        String[] monthArray = new String[12];
        for (int i = 0; i < 12; i++) {
            monthArray[i] = String.valueOf(i + 1);
        }
        return monthArray;
    }

    private String[] makeCurrentMonthArray(int currentMonth) {
        int count = 12 - currentMonth + 1;
        String[] currentMonthArray = new String[count];
        for (int i = 0; i < count; i++) {
            currentMonthArray[i] = String.valueOf(currentMonth);
            currentMonth++;
        }
        return currentMonthArray;
    }

    // 그 해, 다음 해, 다다음 해

    private String[] makeLastYearArray(int currentYear, int yearAfterThree) {
        int value = yearAfterThree - currentYear;
        String[] yearArray = new String[value];
        for (int i = currentYear; i < yearAfterThree; i++) {
            yearArray[i - currentYear] = String.valueOf(i);
        }
        return yearArray;
    }

    private String[] makeLastMonthArray(int monthAfterThree) {
        String[] yearArray = new String[monthAfterThree];
        for (int i = 0; i < monthAfterThree; i++) {
            yearArray[i] = String.valueOf(i + 1);
        }
        return yearArray;
    }

    private String[] makeLastDayArray(int currentMonth, int dayAfterThree) {
        int value = currentMonth - dayAfterThree;
        String[] yearArray = new String[value + 1];
        for (int i = currentMonth; i < dayAfterThree; i++) {
            yearArray[i] = String.valueOf(currentMonth);
        }
        return yearArray;
    }

}
