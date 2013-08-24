package pe.joedayz.viajero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
	}

	public void seleccionarOpcion(View v){
//		TextView textView = (TextView) v;
//		String opcion = "Opcion " + textView.getText().toString();
//		Toast.makeText(this, opcion, Toast.LENGTH_LONG).show();
		switch (v.getId()) {
		case R.id.nuevo_viaje:
			startActivity(new Intent(this, ViajeActivity.class));
			break;
		case R.id.nuevo_gasto:
			startActivity(new Intent(this, GastoActivity.class));
			break;
		default:
			break;
		}
		
	}

}
