package util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class Localizador {


	private Context context;
	
	public Localizador(Context context){
		this.context = context;
	}
	
	public LatLng getCoordenada(String direccion) {
		Geocoder geocoder = new Geocoder(context);
		
		try {
			List<Address> direcciones = 
					geocoder.getFromLocationName(direccion, 1);
			if(!direcciones.isEmpty()){
				Address direccionLocalizada = direcciones.get(0);
				double latitude = direccionLocalizada.getLatitude();
				double longitude = direccionLocalizada.getLongitude();
				
				return new LatLng(latitude, longitude);
			} else{
				return null;
			}
		} catch (IOException e) {
			return null;
		}
		
	}
}
