package id.dicoding.mirel.logindengansqllite;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    LocationManager lokasiManeger;
    double latitude;
    double langtude;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        lokasiManeger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = lokasiManeger.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.d("GPSApp", "User tidak mengijinkan akses lokasi");
            return;
        }
        Location location=lokasiManeger.getLastKnownLocation(provider);
        if (location !=null){
            System.out.println("provider"+provider+"Ditentukan");
            latitude =location.getLatitude();
            langtude =location.getLongitude();
            onLocationChanged(location);
        }else{
            Log.d("Erorr","lokasi belum tersedia");
        }
    }
    public void btnmaps(View v){
        if(v.getTag().equals("posisi") ){
            LatLng pos = new LatLng(latitude,langtude);
            mMap.addMarker(new MarkerOptions().position(pos).title("KAMU BERADA DI SINI"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

        }else if(v.getTag().equals("koordinat")){
            Toast.makeText(this, "Koordinat  Latitude: " + latitude + " Longitude: "+langtude ,
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0, 0);
        mMap.addMarker(new MarkerOptions().position(sydney).title("PLIH POSISI KAMU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lokasiManeger.requestLocationUpdates(provider,400,1,this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lokasiManeger.removeUpdates(this);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this,"ENABLE NEW PROVIDER"+provider,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this,"DISABLE NEW PROVIDER"+provider,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        langtude=location.getLongitude();
        latitude=location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}