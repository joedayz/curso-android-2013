package pe.joedayz.registroalumnoslandscape;

import java.util.List;

import pe.joedayz.registroalumnoslandscape.dao.AlumnoDAO;
import pe.joedayz.registroalumnoslandscape.modelo.Alumno;
import pe.joedayz.registroalumnoslandscape.task.EnviaAlumnosTask;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

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
					int position, long id) {
				Alumno alumnoSeleccionado = (Alumno) adapter
						.getItemAtPosition(position);
				Intent irParaFormulario = new Intent(ListaAlumnosActivity.this,
						FormularioActivity.class);
				irParaFormulario.putExtra("alumnoSeleccionado",
						alumnoSeleccionado);
				startActivity(irParaFormulario);
			}

		});
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicion, long id) {
				alumno = (Alumno) adapter.getItemAtPosition(posicion);
				// Toast.makeText(ListaAlumnosActivity.this,
				// "Clic largo en " +
				// alumno,
				// Toast.LENGTH_SHORT).show();
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

		// int layout = android.R.layout.simple_list_item_1;
		// usamos ahora nuestro layout
		int layout = R.layout.linea_listade;

		ListaAlumnosAdapter adapter = new ListaAlumnosAdapter(alumnos, this);

		LayoutInflater inflater = getLayoutInflater();

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
		case R.id.enviar_alumnos:

			EnviaAlumnosTask task = 
						new EnviaAlumnosTask(this);
			task.execute();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuItem llamar = menu.add("Llamar");
		llamar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				Intent irALlamar = new Intent(Intent.ACTION_CALL);
				Uri llamarA = Uri.parse("tel:" + alumno.getTelefono());
				irALlamar.setData(llamarA);

				startActivity(irALlamar);
				return false;
			}
		});
		menu.add("Enviar SMS");
		MenuItem site = menu.add("Navegar a");
		site.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irASite = new Intent(Intent.ACTION_VIEW);
				Uri site = Uri.parse("http://" + alumno.getSite());
				irASite.setData(site);

				startActivity(irASite);
				return false;
			}
		});

		MenuItem eliminar = menu.add("Eliminar");
		eliminar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

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
