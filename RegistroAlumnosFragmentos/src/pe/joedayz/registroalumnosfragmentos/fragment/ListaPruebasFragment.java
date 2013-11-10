package pe.joedayz.registroalumnosfragmentos.fragment;


import java.util.Arrays;
import java.util.List;

import pe.joedayz.registroalumnosfragmentos.R;
import pe.joedayz.registroalumnosfragmentos.modelo.Prueba;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaPruebasFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View listaDePruebas = inflater.inflate(R.layout.pruebas_lista, 
				container, false);
		
		
		ListView listaViewEnPruebas = (ListView) 
				listaDePruebas.findViewById(R.id.pruebas);
		
		Prueba prueba1 = new Prueba("20/11/2013", "Calculo");
		prueba1.setTopicos(Arrays.asList("Algebra lineal", "Integrales",
				"Ecuaciones"));
		
		Prueba prueba2 = new Prueba("25/11/2013", "Ingles");
		prueba2.setTopicos(Arrays.asList("to-be", "pronombres",
				"posesivos"));
		
		List<Prueba> pruebas = Arrays.asList(prueba1, prueba2);
		int layout = android.R.layout.simple_list_item_1;
		FragmentActivity context = getActivity();
		ArrayAdapter<Prueba> adapter =
				new ArrayAdapter<Prueba>(context, layout, pruebas);
		
		listaViewEnPruebas.setAdapter(adapter);
		
		
		
		return listaDePruebas;
	}
}
