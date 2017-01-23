package org.thomaschen.leave;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    public static final String ALARM_PREFERENCES = "AlarmPrefs";
    SharedPreferences settings;
    SharedPreferences.Editor prefEditor;

    public ArrayList<Alarm> alarmList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializePref();

        String className = getIntent().getStringExtra("caller");

        //Toast.makeText(this, className, Toast.LENGTH_SHORT).show();

        if (className.contains("MapActivity")) {

            //Toast.makeText(this, "after", Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = getSharedPreferences("AlarmPrefs", MODE_PRIVATE);
            String label = prefs.getString("Label","Default");
            String finalAlarm = prefs.getString("FinalAlarm","Default");
            String triggerAlarm = prefs.getString("TriggerAlarm","Default");
            String txtAddress = prefs.getString("TextAddress","Default");

            alarmList.add(new Alarm(label, txtAddress, finalAlarm, triggerAlarm));

            //Toast.makeText(this, alarmList.get(1).toString(), Toast.LENGTH_SHORT).show();
        }


        Button settingsBtn = (Button) findViewById(R.id.btnSetting);
        ImageButton addBtn = (ImageButton) findViewById(R.id.btnAdd);

        Switch switchBtn = (Switch) findViewById(R.id.switch1);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(i, 1);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ClockActivity.class);
                startActivityForResult(i, 1);
            }
        });

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ConstraintLayout alarm = (ConstraintLayout) buttonView.getParent();

                if (!isChecked) {
                    for (int i = 0; i < alarm.getChildCount(); i++) {
                        if (alarm.getChildAt(i) instanceof TextView) {
                            ((TextView) alarm.getChildAt(i)).setTextColor(getResources().getColor(R.color.subduedTextColor));
                        }

                        if (alarm.getChildAt(i) instanceof TextClock) {
                            ((TextClock) alarm.getChildAt(i)).setTextColor(getResources().getColor(R.color.subduedTextColor));
                        }
                    }

                } else {
                    for (int i = 0; i < alarm.getChildCount(); i++) {
                        if (alarm.getChildAt(i) instanceof TextView) {
                            ((TextView) alarm.getChildAt(i)).setTextColor(getResources().getColor(R.color.white));
                        }

                        if (alarm.getChildAt(i) instanceof TextClock) {
                            ((TextClock) alarm.getChildAt(i)).setTextColor(getResources().getColor(R.color.white));
                        }
                    }
                }

            }
        });


        /*
        ConstraintLayout alarm = (ConstraintLayout) switchBtn.getParent();

        alarm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        */
    }

    public void initializePref() {
        SharedPreferences settings = getSharedPreferences("AlarmPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString("Label", "");
        prefEditor.putString("FinalAlarm", "");
        prefEditor.putString("TriggerAlarm", "");
        prefEditor.putString("TextAddress", "");
        prefEditor.commit();
    }

}

