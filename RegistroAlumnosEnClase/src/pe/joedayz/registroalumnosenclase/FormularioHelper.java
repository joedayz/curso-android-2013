package pe.joedayz.registroalumnosenclase;

import pe.joedayz.registroalumnosenclase.modelo.Alumno;
import android.widget.EditText;
import android.widget.RatingBar;

public class FormularioHelper {

	private EditText editNombre;
	private EditText editSite;
	private EditText editTelefono;
	private EditText editDireccion;
	private RatingBar ratingNota;

	public FormularioHelper(FormularioActivity formulario) {
		editNombre = (EditText) formulario.findViewById(R.id.nombre);
		editSite = (EditText) formulario.findViewById(R.id.site);
		editTelefono = (EditText) formulario.findViewById(R.id.telefono);
		editDireccion = (EditText) formulario.findViewById(R.id.direccion);
		ratingNota = (RatingBar) formulario.findViewById(R.id.nota);
	}

	public Alumno guardarAlumnoDeFormulario() {
		Alumno alumno = new Alumno();
		alumno.setNombre(editNombre.getText().toString());
		alumno.setSite(editNombre.getText().toString());
		alumno.setTelefono(editNombre.getText().toString());
		alumno.setDireccion(editNombre.getText().toString());
		alumno.setNota(Double.valueOf(ratingNota.getRating()));
		
		return alumno;
		
	}

}
