package com.example.registroalumnosenclasefinal;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.registroalumnosenclasefinal.dao.AlumnoDAO;
import com.example.registroalumnosenclasefinal.modelo.Alumno;

public class ListaAlumnosActivity extends Activity {

	private ListView lista;
	private Alumno alumno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listade_alumnos);
		
	
		lista = (ListView) findViewById(R.id.lista);
		
		registerForContextMenu(lista);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, 
					int position,
					long id) {
				Alumno alumnoSeleccionado = 
						(Alumno) adapter.getItemAtPosition(position);
				Intent irParaFormulario = new Intent(ListaAlumnosActivity.this, FormularioActivity.class);
				irParaFormulario.putExtra("alumnoSeleccionado", alumnoSeleccionado);
				startActivity(irParaFormulario);
			}
			
		});
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			

			@Override
			public boolean onItemLongClick(AdapterView<?>
					adapter, View view,
					int posicion, long id) {
				alumno = (Alumno) adapter.getItemAtPosition(posicion);
				Toast.makeText(ListaAlumnosActivity.this,
						"Clic largo en " + 
				alumno, 
				Toast.LENGTH_SHORT).show();
				return false;
			}
		

	
			
		});		
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		
		cargarLista();
	}


	private void cargarLista() {
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

	
	


	@Override
	public void onCreateContextMenu(ContextMenu menu, 
			View v,
			ContextMenuInfo menuInfo) {
		
		menu.add("Matricular");
		menu.add("Enviar SMS");
		menu.add("Navegar en el site");
		MenuItem eliminar = menu.add("Eliminar");
		eliminar.setOnMenuItemClickListener( new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlumnoDAO dao = new AlumnoDAO(ListaAlumnosActivity.this);
				dao.eliminar(alumno);
				dao.close();
				
				cargarLista();
				return false;
			}
		});
		menu.add("Ver en el mapa");
		menu.add("Enviar en el email");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	
	
	
}
