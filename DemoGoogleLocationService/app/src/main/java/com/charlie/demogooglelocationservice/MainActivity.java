package com.charlie.demogooglelocationservice;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements LocationListener {

    GoogleMap googleMap;
    private  LatLng location;
    private CircleOptions circleOptions;
    private Circle circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GoogleLocationService service=GoogleLocationService.getLocationManager(getApplicationContext(), MainActivity.this,0,1);

        // if permission is not granted or location service is not ready then stop the activaty
        if( !service.getPermissionStatus() || !service.getLocationServiceStatus()){
            onStop();
        }

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.gpsMap)).getMap();
            }

            location=new LatLng(service.getLastKnownLocation().getLatitude(),service.getLastKnownLocation().getLongitude());

            //googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(service.getLastKnownLocation().getLatitude(), service.getLastKnownLocation().getLongitude()), 15));
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            circleOptions = new CircleOptions()
                    .radius(50)   //set radius in meters
                    .fillColor(Color.BLUE) //default
                    .strokeColor(Color.WHITE)
                    .strokeWidth(5);
            circleOptions.center(location);
            circle=googleMap.addCircle(circleOptions);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        if(circle !=null)
            circle.remove();
        circle=googleMap.addCircle(circleOptions.center(new LatLng(location.getLatitude(),location.getLongitude())));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
