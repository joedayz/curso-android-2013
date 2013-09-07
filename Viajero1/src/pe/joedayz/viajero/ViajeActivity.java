package pe.joedayz.viajero;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ViajeActivity extends Activity {

	private Date fechaLlegada, fechaSalida;
	private int anno, mes, dia;
	private Button fechaLlegadaButton, fechaSalidaButton;
	private EditText destino, cantidadPersonas, presupuesto;
	private RadioGroup radioGroup;
	private DatabaseHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viaje);
		

		Calendar calendar = Calendar.getInstance();
		anno = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		
		fechaLlegadaButton = (Button) findViewById(R.id.fechaLlegada);
		fechaSalidaButton = (Button) findViewById(R.id.fechaSalida);
		
		//recuperando datos
		destino = (EditText) findViewById(R.id.destino);
		cantidadPersonas = (EditText) findViewById(R.id.cantidadpersonas);
		presupuesto = (EditText) findViewById(R.id.presupuesto);
		radioGroup = (RadioGroup) findViewById(R.id.tipoViaje);
		
		//preparacion de la BD
		helper = new DatabaseHelper(this);
	}

	public void seleccionarFecha(View view) {
		showDialog(view.getId());
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case R.id.fechaLlegada:
			return new DatePickerDialog(this, fechaLlegadaListener,
					anno, mes, dia);

		case R.id.fechaSalida:
			return new DatePickerDialog(this, fechaSalidaListener, 
					anno, mes, dia);
		}
		return null;
	}

	private OnDateSetListener fechaLlegadaListener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int anoSelecionado, 
				int mesSelecionado, int diaSelecionado) {
			fechaLlegada = crearDatos(anoSelecionado, mesSelecionado, 
					diaSelecionado);
			fechaLlegadaButton.setText(dia + "/" + (mes + 1) + "/" + anno);
		}
	};

	private OnDateSetListener fechaSalidaListener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
			fechaSalida = crearDatos(anoSelecionado, mesSelecionado,
					diaSelecionado);
			fechaSalidaButton.setText(dia + "/" + (mes + 1) + "/" + anno);
		}
	};

	private Date crearDatos(int anoSelecionado, int mesSelecionado, int diaSelecionado) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(anoSelecionado, mesSelecionado, diaSelecionado);
		return calendar.getTime();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.viaje, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.nuevo_gasto:
			startActivity(new Intent(this, GastoActivity.class));
			return true;
		case R.id.eliminar:
			//eliminar el viaje de la BD
			Toast.makeText(this, "Eliminar viaje de la BD", 
					Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
		
	}
	
	//database methods
	public void guardarViaje(View view){
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("DESTINO", destino.getText().toString());
		values.put("FECHA_LLEGADA", fechaLlegada.getTime());
		values.put("FECHA_SALIDA", fechaSalida.getTime());
		values.put("PRESUPUESTO", presupuesto.getText().toString());
		values.put("CANTIDAD_PERSONAS", cantidadPersonas.getText().toString());
		
		int tipo = radioGroup.getCheckedRadioButtonId();
		
		if(tipo == R.id.placer){
			values.put("TIPO_VIAJE", Constantes.VIAJE_PLACER);
		}else{
			values.put("TIPO_VIAJE", Constantes.VIAJE_NEGOCIOS);
		}
	}
	
	
}
