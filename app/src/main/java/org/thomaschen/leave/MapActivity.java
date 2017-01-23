package org.thomaschen.leave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback{


    EditText etxtDestination;

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        Button btnMapDone = (Button) findViewById(R.id.btnMapDone);

        etxtDestination = (EditText) findViewById(R.id.etxtDestination);

        btnMapDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapActivity.this, TriggerActivity.class);
                //String name = getIntent().getComponent().getClassName();
                //i.putExtra("caller", name);

                //Toast.makeText(MapActivity.this, name, Toast.LENGTH_SHORT).show();

                startActivity(i);
            }
        });

        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = etxtDestination.getText().toString();

                loadMap(location);
            }
        });

        /*
        btnMapDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences("AlarmPrefs", MODE_PRIVATE);
                String label = prefs.getString("Label","");
                String finalAlarm = prefs.getString("FinalAlarm","");
                String triggerAlarm = prefs.getString("TriggerAlarm","");
                String txtAddress = prefs.getString("TextAddress","");


                Debug Tool
                Toast.makeText(MapActivity.this, label, Toast.LENGTH_SHORT).show();
                Toast.makeText(MapActivity.this, finalAlarm, Toast.LENGTH_SHORT).show();
                Toast.makeText(MapActivity.this, triggerAlarm, Toast.LENGTH_SHORT).show();
                Toast.makeText(MapActivity.this, txtAddress, Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
    }

    private void loadMap(String location) {
        List<Address> addresses = null;
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            addresses = geocoder.getFromLocationName(location, 1);
        } catch(Exception ex) {
            Toast.makeText(MapActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        if (addresses == null) {
            return;
        }

        double latitude = 0, longitude = 0;

        if(addresses.size() > 0) {
            latitude= addresses.get(0).getLatitude();
            longitude= addresses.get(0).getLongitude();
        }

        LatLng locations = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions().position(locations).title("Your destination"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locations, 15));

        SharedPreferences prefs = getSharedPreferences("AlarmPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("TextAddress", location);
        editor.commit();

    }
}
