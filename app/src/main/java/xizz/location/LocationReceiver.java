package xizz.location;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.NotificationCompat;

public class LocationReceiver extends BroadcastReceiver {

	private static int sNum = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		Location loc = intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
		if (loc != null) {
			sendNotification(context, loc);
		}
	}

	private void sendNotification(Context context, Location location) {
		// create the activity to be opened after c licking notification
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setSmallIcon(android.R.drawable.stat_notify_chat)
			.setContentTitle("Location Update #" + sNum)
			.setContentText("Location: " + location.getLatitude() + ", " + location.getLongitude());

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(sNum++, builder.build());
	}

}
