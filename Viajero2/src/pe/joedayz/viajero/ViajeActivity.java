package pe.joedayz.viajero;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
	private int ano, mes, dia;
	private Button fechaLlegadaButton, fechaSalidaButton;
	private DatabaseHelper helper;
	private EditText destino, cantidadPersonas, presupuesto;
	private RadioGroup radioGroup;
	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viaje);

		Calendar calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		
		fechaLlegadaButton = (Button) findViewById(R.id.fechaLlegada);
		fechaSalidaButton = (Button) findViewById(R.id.fechaSalida);
		
		destino = (EditText) findViewById(R.id.destino);
		cantidadPersonas = (EditText) findViewById(R.id.cantidadpersonas);
		presupuesto = (EditText) findViewById(R.id.presupuesto);
		
		radioGroup = (RadioGroup) findViewById(R.id.tipoViaje);
		
		helper = new DatabaseHelper(this);
		
		id = getIntent().getStringExtra(Constantes.VIAJE_ID);
		
		if(id != null){
			prepararEdicion();
		}
	}

	private void prepararEdicion() {
		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor cursor =
				db.rawQuery("SELECT TIPO_VIAJE, DESTINO, FECHA_LLEGADA, " +
				"FECHA_SALIDA, CANTIDAD_PERSONAS, PRESUPUESTO " +
				"FROM VIAJE WHERE _ID = ?", new String[]{id});
		cursor.moveToFirst();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if(cursor.getInt(0) == Constantes.VIAJE_PLACER){
			radioGroup.check(R.id.placer);
		}else{
			radioGroup.check(R.id.negocios);
		}

		destino.setText(cursor.getString(1));
		fechaLlegada = new Date(cursor.getLong(2));
		fechaSalida = new Date(cursor.getLong(3));
		fechaLlegadaButton.setText(dateFormat.format(fechaLlegada));
		fechaSalidaButton.setText(dateFormat.format(fechaSalida));
		cantidadPersonas.setText(cursor.getString(4));
		presupuesto.setText(cursor.getString(5));
		cursor.close();
	}

	public void seleccionarFecha(View view) {
		showDialog(view.getId());
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case R.id.fechaLlegada:
			return new DatePickerDialog(this, fechaLlegadaListener, ano, mes, dia);

		case R.id.fechaSalida:
			return new DatePickerDialog(this, fechaSalidaListener, ano, mes, dia);
		}
		return null;
	}
	
	private OnDateSetListener fechaLlegadaListener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
			fechaLlegada = crearFecha(anoSelecionado, mesSelecionado, diaSelecionado);
			fechaLlegadaButton.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};

	private OnDateSetListener fechaSalidaListener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
			fechaSalida = crearFecha(anoSelecionado, mesSelecionado, diaSelecionado);
			fechaSalidaButton.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};

	private Date crearFecha(int anoSelecionado, int mesSelecionado, int diaSelecionado) {
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
		case R.id.remover:
			eliminarViaje(id);
			finish();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}
	
	private void eliminarViaje(String id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		String where []  = new String[]{id};
		db.delete("GASTO", "VIAJE_ID = ?", where);
		db.delete("VIAJE", "_ID = ?", where);
	}
	
	public void crearViaje(View view){
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("DESTINO", destino.getText().toString());
		values.put("FECHA_LLEGADA", fechaLlegada.getTime());
		values.put("FECHA_SALIDA", fechaSalida.getTime());
		values.put("PRESUPUESTO", presupuesto.getText().toString());
		values.put("CANTIDAD_PERSONAS", 
						cantidadPersonas.getText().toString());
		
		int tipo = radioGroup.getCheckedRadioButtonId();
		
		if(tipo == R.id.placer){
			values.put("tipo_viaje", Constantes.VIAJE_PLACER);
		}else{
			values.put("tipo_viaje", Constantes.VIAJE_NEGOCIOS);
		}
		
		long resultado;
		
		if(id == null){
			resultado = db.insert("VIAJE", null, values);
		}else{
			resultado = db.update("VIAJE", values, "_ID = ?", new String[]{ id });
		}
		
		if(resultado != -1 ){
			Toast.makeText(this, getString(R.string.registro_guardar), Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, getString(R.string.error_guardar), Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		helper.close();
		super.onDestroy();
	}
	
}
