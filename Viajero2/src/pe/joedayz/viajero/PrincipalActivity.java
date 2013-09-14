package pe.joedayz.viajero;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
	}

	public void selecionarOpcao(View view) {
		switch (view.getId()) {
		case R.id.nuevo_viaje:
			startActivity(new Intent(this, ViajeActivity.class));
			break;
		case R.id.nuevo_gasto:
			SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
			boolean modoViagem = preferencias.getBoolean(Constantes.MODO_VIAJE, false);
			
			if(modoViagem){
				//obter o id da viagem atual
				int viagemAtual = 1;
				String destino = "Lima";
				Intent intent = new Intent(this, GastoActivity.class);
				intent.putExtra(Constantes.VIAJE_ID, viagemAtual);
				intent.putExtra(Constantes.VIAJE_DESTINO, destino);
				startActivity(intent);
			}else{
				Intent intent = new Intent(this, ViajeListActivity.class);
				intent.putExtra(Constantes.MODO_SELECCIONAR_VIAJE, true);
				startActivityForResult(intent, 0);
			}
			break;
		case R.id.mis_viajes:
			startActivity(new Intent(this, ViajeListActivity.class));
			break;
		case R.id.configuraciones:
			startActivity(new Intent(this, ConfiguracionesActivity.class));
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK){
			String id = data.getExtras().getString(Constantes.VIAJE_ID);
			String destino = data.getExtras().getString(Constantes.VIAJE_DESTINO);
			
			Intent intent = new Intent(this, GastoActivity.class);
			intent.putExtra(Constantes.VIAJE_ID, id);
			intent.putExtra(Constantes.VIAJE_DESTINO, destino);
			startActivity(intent);
		}
		else{
			Toast.makeText(this, getString(R.string.error_seleccionar_viaje), 
								 Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		finish();
		return true;
	}
}