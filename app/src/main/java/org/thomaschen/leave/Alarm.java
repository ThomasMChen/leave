package org.thomaschen.leave;

import android.location.Location;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tom on 1/22/17.
 */

public class Alarm {

    private String label;
    private String textAddress;
    private String destinationTime;
    private String triggerTime;

    public Alarm() {

    }

    public Alarm(String lab, String address, String time, String trigger) {
        label = lab;
        textAddress = address;
        destinationTime = time;
        triggerTime = trigger;
    }

    public String getLabel() {
        return label;
    }

    public String getAddress() {
        return textAddress;
    }

    public String getDestTime() {
        return destinationTime;
    }

    public String getTrigTime() {
        return triggerTime;
    }

    public String toString() {
        return "Label: " + label + " Address: " + textAddress + " Arrival Time: " + destinationTime + " Trigger Time" + triggerTime;
    }

    public void addAlarmCard() {

    }
}
