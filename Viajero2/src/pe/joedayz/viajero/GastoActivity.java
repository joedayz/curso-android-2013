package pe.joedayz.viajero;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class GastoActivity extends Activity {

	private int ano, mes, dia;
	private Button dataGasto;
	private Spinner categoria;
	private TextView destino;
	private EditText valor;
	private EditText descripcion;
	private EditText local;
	private Date fecha;
	private DatabaseHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gasto);
		Calendar calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);

		dataGasto = (Button) findViewById(R.id.fecha);
		dataGasto.setText(dia + "/" + (mes + 1) + "/" + ano);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.categoria_gasto,
				android.R.layout.simple_spinner_item);
		categoria = (Spinner) findViewById(R.id.categoria);
		categoria.setAdapter(adapter);
		
		String viajeDestino = getIntent().getExtras().getString(Constantes.VIAJE_DESTINO);
		destino = (TextView) findViewById(R.id.destino);
		destino.setText(viajeDestino);
		
		valor = (EditText) findViewById(R.id.valor);
		descripcion = (EditText) findViewById(R.id.descripcion);
		local = (EditText) findViewById(R.id.local);
		
		helper = new DatabaseHelper(this);
	}

	public void seleccionarFecha(View view) {
		showDialog(view.getId());
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (R.id.fecha == id) {
			return new DatePickerDialog(this, listener, ano, mes, dia);
		}
		return null;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gasto_menu, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		finish();
		return true;
	}

	private OnDateSetListener listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			dataGasto.setText(dia + "/" + (mes + 1) + "/" + ano);
			fecha = crearFecha(ano, mes, dia);
		}
	};
	
	public void registrarGasto(View view){
		ContentValues values = new ContentValues();
		values.put("valor", valor.getText().toString());
		values.put("descripcion", descripcion.getText().toString());
		values.put("local", local.getText().toString());
		values.put("categoria", categoria.getSelectedItem().toString());
		
		SQLiteDatabase db = helper.getWritableDatabase();
		db.insert("gasto", null, values);
	}
	
	private Date crearFecha(int anoSelecionado, int mesSelecionado, int diaSelecionado) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(anoSelecionado, mesSelecionado, diaSelecionado);
		return calendar.getTime();
	}
}
