package pe.joedayz.googlemapsgps.fragment;

import pe.joedayz.googlemapsgps.R;
import pe.joedayz.googlemapsgps.modelo.Prueba;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DetallePruebaFragment extends Fragment{

	private Prueba prueba;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View layout = inflater.inflate(R.layout.pruebas_detalle,
				container, false);
		
		buscaComponentesEnLayout();

        populaLayout(layout);
        
		return layout;
	}

	private void populaLayout(View layout) {
		if (this.prueba != null) {
            TextView materia = (TextView) layout
                    .findViewById(R.id.detalle_prueba_materia);
            TextView data = (TextView) layout
                    .findViewById(R.id.detalle_prueba_fecha);
            ListView topicos = (ListView) layout
                    .findViewById(R.id.detalle_prueba_topicos);

            materia.setText(this.prueba.getMateria());
            data.setText(this.prueba.getFecha());

            ArrayAdapter<String> adapter = 
                new ArrayAdapter<String>(
                    getActivity(), 
                    android.R.layout.simple_list_item_1,
                    prueba.getTopicos());

            topicos.setAdapter(adapter);
        }
	}

	private void buscaComponentesEnLayout() {
		if  (getArguments() != null) {
            this.prueba = (Prueba) getArguments().getSerializable("prueba");
        }
	}
}
