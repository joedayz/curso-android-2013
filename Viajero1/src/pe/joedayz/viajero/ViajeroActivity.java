package pe.joedayz.viajero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ViajeroActivity extends Activity {

	private EditText usuario;
	private EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		usuario = (EditText) findViewById(R.id.usuario);
		password = (EditText) findViewById(R.id.password);
	}

	public void entrarOnClick(View v){
		String usuarioDato = usuario.getText().toString();
		String passwordDato = password.getText().toString();
		
		if(usuarioDato.equals("joe") && 
				passwordDato.equals("123")){
			//anda a la siguiente actividad
			startActivity(new Intent(this, PrincipalActivity.class));
		}else{
			String mensajeError = 
					getString(R.string.error_autenticacion);
			Toast.makeText(this, mensajeError, 
					Toast.LENGTH_SHORT).show();
		}
	}

}
