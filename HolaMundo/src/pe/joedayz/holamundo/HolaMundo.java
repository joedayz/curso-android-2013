package pe.joedayz.holamundo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
				
			}
		});
	}

	

}
