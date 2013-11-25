package pe.joedayz.googlemapsgps;

import pe.joedayz.googlemapsgps.fragment.MapaFragment;
import pe.joedayz.googlemapsgps.mapa.ActualizadorPosicion;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MuestraAlumnosProximos extends FragmentActivity {

	

	private ActualizadorPosicion actualizador;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.map_layout);
		
		FragmentManager manager = 
				 getSupportFragmentManager();
		
		FragmentTransaction transaction = manager.beginTransaction();
		MapaFragment mapaFragment = new MapaFragment();
		transaction.replace(R.id.mapa, mapaFragment);
		transaction.commit();
		
		actualizador = new ActualizadorPosicion(this, mapaFragment);
		

		
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		actualizador.cancelar();
	}
	
}
