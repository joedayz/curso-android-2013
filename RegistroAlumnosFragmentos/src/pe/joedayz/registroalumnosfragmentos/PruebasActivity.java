package pe.joedayz.registroalumnosfragmentos;

import pe.joedayz.registroalumnosfragmentos.fragment.DetallePruebaFragment;
import pe.joedayz.registroalumnosfragmentos.fragment.ListaPruebasFragment;
import pe.joedayz.registroalumnosfragmentos.modelo.Prueba;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class PruebasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.pruebas);

		if (savedInstanceState == null) {

			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			if (isTablet()) {
				transaction.replace(R.id.lista_pruebas_fragment, new ListaPruebasFragment(),
						ListaPruebasFragment.class.getCanonicalName());
				transaction.replace(R.id.detalle_fragment, new DetallePruebaFragment(),
						DetallePruebaFragment.class.getCanonicalName());
			} else {
				transaction.replace(R.id.unico, new ListaPruebasFragment(),
						ListaPruebasFragment.class.getCanonicalName());
			}

			transaction.commit();
		}

	}

	private boolean isTablet() {

		return new Boolean(getResources().getBoolean(R.bool.isTablet));
	}

	public void seleccionarPrueba(Prueba prueba) {
		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prueba", prueba);

		DetallePruebaFragment detallePrueba = new DetallePruebaFragment();
		detallePrueba.setArguments(argumentos);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();


		if (!isTablet()) {
			transaction.replace(R.id.unico, detallePrueba,
					DetallePruebaFragment.class.getCanonicalName());
			transaction.addToBackStack(null);
		}else{
			transaction.replace(R.id.detalle_fragment, detallePrueba,
					DetallePruebaFragment.class.getCanonicalName());
		}

		transaction.commit();
	}

}
