package pe.joedayz.googlemapsgps.mapa;

import pe.joedayz.googlemapsgps.fragment.MapaFragment;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

public class ActualizadorPosicion implements LocationListener {

	private LocationManager locationManager;
	private MapaFragment mapa;

	public ActualizadorPosicion(Activity activity, MapaFragment mapa) {
		this.mapa = mapa;
		locationManager = (LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE);

		String provider = LocationManager.GPS_PROVIDER;
		long tiempoMinimo = 20000; // ms
		float distanciaMinima = 20; // m
		locationManager.requestLocationUpdates(provider, tiempoMinimo,
				distanciaMinima, this);
	}

	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		LatLng local = new LatLng(latitude, longitude);
		
		mapa.central(local);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// GPS WIFI

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	public void cancelar() {
		locationManager.removeUpdates(this);
		
	}

}
