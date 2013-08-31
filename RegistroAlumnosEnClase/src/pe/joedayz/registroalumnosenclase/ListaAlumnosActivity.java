package pe.joedayz.registroalumnosenclase;

import java.util.List;

import pe.joedayz.registroalumnosenclase.dao.AlumnoDAO;
import pe.joedayz.registroalumnosenclase.modelo.Alumno;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlumnosActivity extends Activity {

	private ListView lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listade_alumnos);
		
	
		lista = (ListView) findViewById(R.id.lista);
		
		
		lista.setOnItemClickListener(new OnItemClickListener() {

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
	protected void onResume() {
		super.onResume();
		
		AlumnoDAO dao = new AlumnoDAO(this);

		List<Alumno> alumnos = dao.getLista();
		dao.close();

		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<Alumno> adapter = new ArrayAdapter<Alumno>(this, layout,
				alumnos);

		lista.setAdapter(adapter);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_alumnos, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int itemSeleccionado = item.getItemId();
		switch (itemSeleccionado) {
		case R.id.nuevo:
			Intent irParaFormulario = new Intent(this, FormularioActivity.class);
			startActivity(irParaFormulario);
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
