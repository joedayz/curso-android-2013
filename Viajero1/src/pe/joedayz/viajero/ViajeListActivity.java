package pe.joedayz.viajero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

public class ViajeListActivity extends ListActivity implements
		OnItemClickListener {
	
	private List<Map<String, Object>> viajes;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		String[] de = {"imagen", "destino", "fecha", "total"};
		int[] para = {R.id.tipoViaje, R.id.destino, R.id.fecha, R.id.valor};
		
		SimpleAdapter adapter = new SimpleAdapter(this,
				listarViajes(), R.layout.lista_viaje, de, para);
		setListAdapter(adapter);
		
//		setListAdapter(new ArrayAdapter<String>(this, 
//				android.R.layout.simple_list_item_1, listarViajes()));
//		ListView listView = getListView();
//		listView.setOnItemClickListener(this);
		
	}

//	private List<String>listarViajes() {
//		return Arrays.asList("Lima","Arequipa","Piura", "Juliaca");
//	}
	
	private List<Map<String, Object>> listarViajes() {
		viajes = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("imagen", R.drawable.negocios);
		item.put("destino", "Lima");
		item.put("fecha","02/02/2012 a 04/02/2012");
		item.put("total","Gasto total S/. 314,98");
		viajes.add(item);
		
		item = new HashMap<String, Object>();
		item.put("imagen", R.drawable.lazer);
		item.put("destino", "Uruguay");
		item.put("fecha","14/05/2012 a 22/05/2012");
		item.put("total","Gasto total S/. 25834,67");
		viajes.add(item);
		
		return viajes;
	}

	@Override
	public void onItemClick(AdapterView<?> parent,
			View view, int position, long id) {
//		TextView textView = (TextView) view;
//		String mensaje = "Viaje seleccionado " + textView.getText().toString();
//		Toast.makeText(getApplicationContext(), mensaje, 
//				Toast.LENGTH_SHORT).show();

	}

}
