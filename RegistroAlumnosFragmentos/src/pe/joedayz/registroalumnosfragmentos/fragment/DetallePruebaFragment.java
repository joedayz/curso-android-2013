package pe.joedayz.registroalumnosfragmentos.fragment;

import pe.joedayz.registroalumnosfragmentos.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetallePruebaFragment extends Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View viewDetalles = inflater.inflate(R.layout.pruebas_detalle,
				container, false);
		
		return viewDetalles;
	}
}
