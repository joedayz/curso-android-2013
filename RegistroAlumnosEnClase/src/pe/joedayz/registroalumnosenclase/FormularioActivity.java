package pe.joedayz.registroalumnosenclase;

import pe.joedayz.registroalumnosenclase.modelo.Alumno;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

public class FormularioActivity extends Activity {

	FormularioHelper helper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		helper = new FormularioHelper(this);


		helper.guardarAlumnoDeFormulario();



	}

}
