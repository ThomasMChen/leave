package org.thomaschen.leave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import static org.thomaschen.leave.MainActivity.ALARM_PREFERENCES;
import static org.thomaschen.leave.R.drawable.settings;

public class ClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        Button doneBtn = (Button) findViewById(R.id.btnDone);
        Button cancelBtn = (Button) findViewById(R.id.btnCancel);
        final EditText label = (EditText) findViewById(R.id.etextLabel);
        final TimePicker time = (TimePicker) findViewById(R.id.timePicker);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLabel = label.getText().toString();
                String userTime = time.getHour() + ":" + time.getMinute();

                SharedPreferences prefs = getSharedPreferences("AlarmPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Label", userLabel);
                editor.putString("FinalAlarm", userTime);
                editor.commit();

                Intent i = new Intent(ClockActivity.this, MapActivity.class);
                startActivity(i);
            }
        });



    }


}
