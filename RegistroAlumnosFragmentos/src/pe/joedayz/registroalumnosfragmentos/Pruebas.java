package pe.joedayz.registroalumnosfragmentos;

import pe.joedayz.registroalumnosfragmentos.fragment.DetallePruebaFragment;
import pe.joedayz.registroalumnosfragmentos.fragment.ListaPruebasFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Pruebas extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.pruebas);
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		
		if(isTabletEnLandscape()){
			transaction.replace(R.id.izquierda, new ListaPruebasFragment());
			//transaction.replace(R.id.derecha, new DetallePruebaFragment());
		}else{
			transaction.replace(R.id.unico, new ListaPruebasFragment());
		}
		
		transaction.commit();
		

	}

	private boolean isTabletEnLandscape() {
		// TODO Auto-generated method stub
		return true;
	}
}
