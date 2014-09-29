package pe.joedayz.holamundo;

import pe.joedayz.holamundo.fragment.DetallePruebaFragment;
import pe.joedayz.holamundo.fragment.ListaPruebasFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class PruebasActivity extends FragmentActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pruebas);
		
		FragmentManager manager = 
				getSupportFragmentManager();
		
		FragmentTransaction beginTransaction = 
				manager.beginTransaction();
		
		if(isTablet()){
			beginTransaction.replace(R.id.pruebas_lista, 
					new ListaPruebasFragment());
			beginTransaction.replace(R.id.pruebas_detalle, 
					new DetallePruebaFragment());			
		}else{
			beginTransaction.replace(R.id.unico, new 
					ListaPruebasFragment());
		}
		
		beginTransaction.commit();

	}

	private boolean isTablet() {
		return true;
	}




}
