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

		EditText caja = (EditText) findViewById(R.id.caja);
		
		Button boton = (Button) findViewById(R.id.boton);
		
		boton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("TAG", "Boton presionado");
				Toast.makeText(HolaMundo.this, 
						"Boton presionado", 
						Toast.LENGTH_LONG).show();
			}
			
		});
	}


}
