package pe.joedayz.registroalumnosenclase;

import pe.joedayz.registroalumnosenclase.dao.AlumnoDAO;
import pe.joedayz.registroalumnosenclase.modelo.Alumno;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends Activity {

	FormularioHelper helper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		Intent intent = getIntent();
		final Alumno alumnoParaSerModificado = (Alumno) 
				intent.getSerializableExtra("alumnoSeleccionado");
		Toast.makeText(this, "Alumno Seleccionado " + alumnoParaSerModificado, Toast.LENGTH_LONG).show();
		
		
		helper = new FormularioHelper(this);
		Button boton = (Button) findViewById(R.id.grabar);
		
		if(alumnoParaSerModificado!=null){
			boton.setText("Modificar");
			helper.colocaAlumnoEnFormulario(alumnoParaSerModificado);
		}
		


		

		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Alumno alumno = helper.guardarAlumnoDeFormulario();

				AlumnoDAO dao = new AlumnoDAO(FormularioActivity.this);
				
			
				if(alumnoParaSerModificado == null){
					dao.grabar(alumno);
				}else{
					alumno.setId(alumnoParaSerModificado.getId());
					dao.actualizar(alumno);
				}
		
				
				dao.close();
				
				
				finish();
			}
		});


	}

}
