package pe.joedayz.googlemapsgps.fragment;

import java.util.List;

import pe.joedayz.googlemapsgps.dao.AlumnoDAO;
import pe.joedayz.googlemapsgps.mapa.Localizador;
import pe.joedayz.googlemapsgps.modelo.Alumno;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {

	@Override
	public void onResume() {

		super.onResume();
		
		FragmentActivity context = getActivity();
		Localizador coderUtil = new Localizador(context);
		LatLng local = 
				coderUtil.getCoordenada("Rua Vergueiro 3185 Vila Mariana");
		central(local);
		
		AlumnoDAO dao = new AlumnoDAO(context);
		List<Alumno> alumnos = dao.getLista();
		dao.close();
		
		for(Alumno alumno: alumnos){
			
			
			LatLng coordenada = new Localizador(context).
					getCoordenada(alumno.getDireccion());

			if(coordenada!=null){
				MarkerOptions marcador = new MarkerOptions()
				.position(coordenada).title(alumno.getNombre())
				.snippet(alumno.getDireccion());
				
				//configurar marcador o titulo
				getMap().addMarker(marcador);
			}

		}
	}

	public void central(LatLng local) {
		GoogleMap map = getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(local, 17);
		map.animateCamera(update);
	}

}
