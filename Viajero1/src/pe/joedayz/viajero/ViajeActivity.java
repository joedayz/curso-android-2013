package pe.joedayz.viajero;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ViajeActivity extends Activity {

	private Date fechaLlegada, fechaSalida;
	private int anno, mes, dia;
	private Button fechaLlegadaButton, fechaSalidaButton;
	
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
	}

	public void seleccionarFecha(View view) {
		showDialog(view.getId());
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case R.id.fechaLlegada:
			return new DatePickerDialog(this, fechaLlegadaListener, anno, mes, dia);

		case R.id.fechaSalida:
			return new DatePickerDialog(this, fechaSalidaListener, anno, mes, dia);
		}
		return null;
	}

	private OnDateSetListener fechaLlegadaListener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
			fechaLlegada = crearDatos(anoSelecionado, mesSelecionado, diaSelecionado);
			fechaLlegadaButton.setText(dia + "/" + (mes + 1) + "/" + anno);
		}
	};

	private OnDateSetListener fechaSalidaListener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
			fechaSalida = crearDatos(anoSelecionado, mesSelecionado, diaSelecionado);
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
	
}
