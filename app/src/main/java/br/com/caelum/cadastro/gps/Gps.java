package br.com.caelum.cadastro.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class Gps extends LocationCallback {
    private final FusedLocationProviderClient client;
    private final GoogleMap map;

    public Gps(Context context, GoogleMap map) {
        this.client = LocationServices.getFusedLocationProviderClient(context);
        this.map = map;
    }


    @SuppressLint("MissingPermission")
    public void initSearch() {
        LocationRequest request = new LocationRequest();

        request.setInterval(1000);
        request.setSmallestDisplacement(5);

        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        client.requestLocationUpdates(request, this, null);
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        Location lastLocation = locationResult.getLastLocation();

        LatLng coords = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 5));

    }
}
