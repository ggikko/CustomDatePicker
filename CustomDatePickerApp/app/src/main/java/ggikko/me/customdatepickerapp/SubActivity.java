package ggikko.me.customdatepickerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class SubActivity extends AppCompatActivity {

    DatePicker datePicker;

    TextView txt1;
    TextView txt2;

    Button btn1;
    Button btn2;

    int year,month,day;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = datePicker.getYear() + "년 " + (datePicker.getMonth()+1) + "월 " + datePicker.getDayOfMonth() + "일";
                year = datePicker.getYear();
                month = (datePicker.getMonth()+1);
                day = datePicker.getDayOfMonth();

                txt1.setText(date);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validDate = isValidDate(year, month, day, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, calendar.get(calendar.DAY_OF_MONTH));

                txt2.setText(year+" "+month+" "+day);

                txt1.setText("result : " + validDate);
            }
        });
    }

    private boolean isValidDate(int firstYear, int firstMonth, int firstDay, int secondYear, int secondMonth, int secondDay){
        if(firstYear > secondYear){
            return false;
        }
        if(firstMonth > secondMonth){
            return false;
        }
        if(firstDay > secondDay){
            return false;
        }
        return true;
    }

}
