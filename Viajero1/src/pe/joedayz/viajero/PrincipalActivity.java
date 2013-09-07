package pe.joedayz.viajero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class PrincipalActivity extends Activity {

	public static final String MODO_VIAGEM = "modo_viaje";
	public static final String VIAJE_ID = "viaje_id";
	public static final String VIAJE_DESTINO = "viaje_destino";
	public static final String MODO_SELECCIONAR_VIAJE = "modo_seleccionar_viaje";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
	}

	public void seleccionarOpcion(View v){

		switch (v.getId()) {
		case R.id.nuevo_viaje:
			startActivity(new Intent(this, ViajeActivity.class));
			break;
		case R.id.nuevo_gasto:
			startActivity(new Intent(this, GastoActivity.class));
			break;
		case R.id.configuraciones:
			startActivity(new Intent(this, ConfiguracionesActivity.class));
			break;
		default:
			break;
		}
		
	}

	
	//menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, 
			MenuItem item) {
		finish();
		return true;
	}
	
	

	
	
}
