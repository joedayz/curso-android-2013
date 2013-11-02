package pe.joedayz.holamundo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HolaMundo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
	
		
		EditText campo = (EditText) findViewById(R.id.campo);
		
		Button boton = (Button) findViewById(R.id.boton);
		
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View clickeado) {
				Log.i("TAG", "Boton clickeado!");
				Toast.makeText(HolaMundo.this,"Hola!", Toast.LENGTH_LONG).show();
			}
		});
		
		Log.i("CICLO DE VIDA", "onCreate");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("CICLO DE VIDA", "onDestroy");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("CICLO DE VIDA", "onPause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("CICLO DE VIDA", "onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("CICLO DE VIDA", "onResume");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("CICLO DE VIDA", "onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("CICLO DE VIDA", "onStop");
	}

	
	

}
