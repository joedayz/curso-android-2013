package pe.joedayz.registroalumnosenclase;

import pe.joedayz.registroalumnosenclase.dao.AlumnoDAO;
import pe.joedayz.registroalumnosenclase.modelo.Alumno;
import android.app.Activity;
import android.os.Bundle;

public class FormularioActivity extends Activity {

	FormularioHelper helper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		helper = new FormularioHelper(this);


		Alumno alumno = helper.guardarAlumnoDeFormulario();

		AlumnoDAO dao = new AlumnoDAO(this);
		dao.grabar(alumno);


	}

}
