package xizz.location;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
			ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
			return;
		}
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, getLocationPendingIntent());
	}

	private PendingIntent getLocationPendingIntent() {
		return PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("ACTION_LOCATION"), PendingIntent.FLAG_UPDATE_CURRENT);
	}
}
