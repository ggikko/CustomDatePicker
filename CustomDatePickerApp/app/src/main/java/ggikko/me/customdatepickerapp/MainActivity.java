package ggikko.me.customdatepickerapp;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ggikko.me.customdatepickerapp.custom.CustomNumberPicker;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    DatePicker customDatePicker;

    CustomNumberPicker numberPicker;

    Button btn2;

    TextView result;

    String[] values = {"매주 월요일", "매주 화요일", "매주 수요일", "매주 목요일", "매주 금요일", "매주 토요일", "매주 일요일"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customDatePicker = (DatePicker) findViewById(R.id.customDatePicker);
        numberPicker = (CustomNumberPicker) findViewById(R.id.numberPicker);

        btn2 = (Button) findViewById(R.id.btn2);
        result = (TextView) findViewById(R.id.result);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(6);
        numberPicker.setDisplayedValues(values);
        numberPicker.getValue();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, SubActivity.class));
                result.setText(values[numberPicker.getValue()]);
            }
        });

//        setDividerColor(numberPicker, R.color.colorAccent);

        final Calendar c = Calendar.getInstance();
        customDatePicker.setMaxDate(c.getTimeInMillis());
        c.add(c.DATE, -20);
        customDatePicker.setMinDate(c.getTimeInMillis());

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(view);
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar cal = new GregorianCalendar(i, i1, i2);
        setDate(cal);
        Toast.makeText(this, "year, month, day : " + i + ", " + i1 +", "+ i2, Toast.LENGTH_SHORT).show();
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.showDate)).setText(dateFormat.format(calendar.getTime()));

    }

    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "das");
    }

    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
