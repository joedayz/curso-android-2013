package pe.joedayz.registroalumnosenclase;

import pe.joedayz.registroalumnosenclase.dao.AlumnoDAO;
import pe.joedayz.registroalumnosenclase.modelo.Alumno;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FormularioActivity extends Activity {

	FormularioHelper helper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		helper = new FormularioHelper(this);


		

		Button boton = (Button) findViewById(R.id.grabar);
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Alumno alumno = helper.guardarAlumnoDeFormulario();

				AlumnoDAO dao = new AlumnoDAO(FormularioActivity.this);
				dao.grabar(alumno);
				dao.close();
				
				
				finish();
			}
		});


	}

}
