package pe.joedayz.registroalumnosenclase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlumnosActivity extends Activity {

	private ListView listaAlumnos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listade_alumnos);
		
		String[] alumnos = {"Hugo", "Paco", "Luis"};
		
		int layout =  android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				layout, alumnos);
		
		listaAlumnos = (ListView) findViewById(R.id.lista);
		
		listaAlumnos.setAdapter(adapter);
		
		listaAlumnos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, 
					int position,
					long id) {
				Toast.makeText(ListaAlumnosActivity.this,
						"Mi texto de aviso", Toast.LENGTH_SHORT).show();
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_alumnos, menu);

		return super.onCreateOptionsMenu(menu);
	}
	

}
