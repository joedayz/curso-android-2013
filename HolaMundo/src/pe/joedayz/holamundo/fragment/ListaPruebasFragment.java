package pe.joedayz.holamundo.fragment;

import java.util.Arrays;
import java.util.List;

import pe.joedayz.holamundo.R;
import pe.joedayz.holamundo.modelo.Prueba;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class ListaPruebasFragment extends Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View listaDePruebas = 
				inflater.inflate(R.layout.pruebas_lista, null);
		
		ListView lista = 
				(ListView) listaDePruebas.findViewById(R.id.pruebas);
		Prueba prueba1 = new Prueba("10/10/2014", "Distribuidos");
		prueba1.setTopicos(Arrays.asList("SOAP", "REST", "Mensajeria"));

		Prueba prueba2 = new Prueba("15/10/2014", "Calculo");
		prueba2.setTopicos(Arrays.asList("Integrales", "Estadistica", "IOP"));

		List<Prueba> pruebas = Arrays.asList(prueba1, prueba2);
		int layout = android.R.layout.simple_list_item_1;

		FragmentActivity context = getActivity();
		ArrayAdapter<Prueba> adapter = 
				new ArrayAdapter<Prueba>(context, layout,
				pruebas);
		lista.setAdapter(adapter);		
		
		return listaDePruebas;
	}
}
