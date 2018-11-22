package com.example.firebear.boussol;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    TextView Longitude_Data;
    TextView Latitude_Data;

    Double longitude, latitude;
    Float[] accel = new Float[3];
    Float[] magn  = new Float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // =============== INITIALISATION DE L'IMAGE =============== //
        image = findViewById(R.id.IMG_COMPASS);
        image.setImageResource(R.drawable.compass);
        // ========================================================= //

        Log.d("=====> ", "START APP");



        // ========================================================= //
        // ============== RECUPERATION DES COORDONNEES ============= //
        // ========================================================= //

        // Liaison des champs
        Longitude_Data = findViewById(R.id.TextView_Longitude_data);
        Latitude_Data = findViewById(R.id.TextView_Latitude_data);

        // Crée notre gestionnaire de localisation
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Vérifie l'activation du GPS
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Log.d("=====> ", "GPS ENABLE");

            // Define a listener that responds to location updates
            LocationListener locationListener = new LocationListener() {

                public void onLocationChanged(Location location) {
                    longitude = location.getLongitude();
                    latitude  = location.getLatitude();
                    Longitude_Data.setText(Double.toString(longitude));
                    Latitude_Data.setText(Double.toString(latitude));
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {}
                public void onProviderEnabled(String provider) {}
                public void onProviderDisabled(String provider) {}
            };

            // Register the listener with the Location Manager to receive location updates
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1340);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1340);
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        else{
            Log.d("=====> ", "GPS DISABLE");
            Toast.makeText(getApplicationContext(), "Cannot get location - GPS disable", Toast.LENGTH_LONG).show();
        }



        // ========================================================= //
        // ===================== SENSOR MANAGER ==================== //
        // ========================================================= //




    }
}
