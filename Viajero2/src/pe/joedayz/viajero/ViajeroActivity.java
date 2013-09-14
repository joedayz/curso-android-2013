package pe.joedayz.viajero;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ViajeroActivity extends Activity {
    private static final String MANTENER_CONECTADO = "mantener_conectado";
	private EditText usuario;
	private EditText password;
	private CheckBox mantenerConectado;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);
        mantenerConectado = (CheckBox) findViewById(R.id.mantenerConectado);
        
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        boolean conectado = preferencias.getBoolean(MANTENER_CONECTADO, false);
        
        if(conectado){
        	startActivity(new Intent(this, PrincipalActivity.class));
        }
    }
    
    public void entrarOnClick(View v){
    	String usuarioInformado = usuario.getText().toString();
    	String senhaInformada = password.getText().toString();
    	
    	if("joe".equals(usuarioInformado)  && "123".equals(senhaInformada)){
    		SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
    		Editor editor = preferencias.edit();
    		editor.putBoolean(MANTENER_CONECTADO, mantenerConectado.isChecked());
    		editor.commit();
    			
    		startActivity(new Intent(this,PrincipalActivity.class));
    	}
    	else{
    		String mensagemErro = getString(R.string.error_autenticacion);
    		Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
    		toast.show();
    	}
    }
}
