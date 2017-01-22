package org.thomaschen.leave;

import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button settingsBtn = (Button) findViewById(R.id.btnSetting);
        ImageButton addBtn = (ImageButton) findViewById(R.id.btnAdd);

        Switch switchBtn = (Switch) findViewById(R.id.switch1);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(i, 1);
            }
        } );

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

        ConstraintLayout alarm = (ConstraintLayout) switchBtn.getParent();

        alarm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });

    }
}
