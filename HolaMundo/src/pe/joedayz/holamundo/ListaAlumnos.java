package pe.joedayz.holamundo;

import java.util.List;

import pe.joedayz.holamundo.dao.AlumnoDAO;
import pe.joedayz.holamundo.modelo.Alumno;
import pe.joedayz.holamundo.task.EnviaAlumnosTask;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ListaAlumnos extends ActionBarActivity {

	private ListView lista;
	private Alumno alumno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listade_alumnos);
		

		lista = (ListView) findViewById(R.id.lista);

		registerForContextMenu(lista);

//		cargarLista();
		
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
//				Toast.makeText(ListaAlumnos.this,
//						"Clic en la posicion " + position, Toast.LENGTH_SHORT)
//						.show();
				Alumno alumnoSeleccionado =
						(Alumno) adapter.getItemAtPosition(position);
				
				Intent irParaFormulario = new Intent(ListaAlumnos.this,
						Formulario.class);
				irParaFormulario.putExtra("alumnoSeleccionado",
						alumnoSeleccionado);
				startActivity(irParaFormulario);
				
				
			}
		});

		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {

				alumno = (Alumno) adapter.getItemAtPosition(position);
//				Toast.makeText(ListaAlumnos.this,
//						"Clic largo en " + adapter.getItemAtPosition(position),
//						Toast.LENGTH_SHORT).show();

				return false;
			}

		});

	}
	
	
	@Override
	protected void onResume() {		
		super.onResume();
		cargarLista();
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		MenuItem llamar = menu.add("Llamar");
		

		
		llamar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaTelefono = new Intent(Intent.ACTION_CALL);
				Uri llamarA = Uri.parse("tel:" + alumno.getTelefono());
				irParaTelefono.setData(llamarA);
				
				startActivity(irParaTelefono);
				return false;
			}
		});
		
		menu.add("Enviar un SMS");
		
		MenuItem site = menu.add("Navegar a");
		
		site.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaSite = new Intent(Intent.ACTION_VIEW);
				Uri localSite = Uri.parse("http://" + alumno.getSite());
				irParaSite.setData(localSite);
				
				startActivity(irParaSite);
				return false;
			}
		});
		
		MenuItem eliminar = menu.add("Eliminar");
		eliminar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlumnoDAO dao = new AlumnoDAO(ListaAlumnos.this);
				dao.eliminar(alumno);
				
				dao.close();
				
				cargarLista();
				
				return false;
			}
		});
		
		
		menu.add("Ver en el mapa");
		menu.add("Enviar un email");
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	

	protected void cargarLista() {
		AlumnoDAO dao = new AlumnoDAO(this);
		List<Alumno> alumnos = dao.getLista();
		dao.close();
		
		//int layout = R.layout.linea_listade; //android.R.layout.simple_list_item_1;
//		ArrayAdapter<Alumno> arrayAdapter = 
//				new ArrayAdapter<Alumno>(this,
//				layout, alumnos);
		
		ListaAlumnosAdapter adapter = new
				ListaAlumnosAdapter(alumnos, this);
		
		
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
			Intent irParaFormulario = new Intent(this, Formulario.class);
			startActivity(irParaFormulario);
			break;
		case R.id.enviar_alumnos:
			
			EnviaAlumnosTask task = new EnviaAlumnosTask(this);
			task.execute();
			break;
		case R.id.recibir_pruebas:
			
			Intent irParaPruebas = 
				new Intent(this, PruebasActivity.class);
			startActivity(irParaPruebas);
			break;
		
		case R.id.mapa:
			Intent irParaMapa = new Intent(this, MuestraAlumnosProximos.class);
			startActivity(irParaMapa);
				
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
