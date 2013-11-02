package pe.joedayz.registroalumnosasincrono;

import pe.joedayz.registroalumnosasincrono.modelo.Alumno;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class FormularioHelper {

	private EditText editNombre;
	private EditText editSite;
	private EditText editTelefono;
	private EditText editDireccion;
	private RatingBar ratingNota;
	private ImageView foto;
	private Alumno alumno;
	
	
	public FormularioHelper(FormularioActivity formulario) {
		editNombre = (EditText) formulario.findViewById(R.id.nombre);
		editSite = (EditText) formulario.findViewById(R.id.site);
		editTelefono = (EditText) formulario.findViewById(R.id.telefono);
		editDireccion = (EditText) formulario.findViewById(R.id.direccion);
		ratingNota = (RatingBar) formulario.findViewById(R.id.nota);
		foto = (ImageView) formulario.findViewById(R.id.foto);
		alumno = new Alumno();
	}

	public Alumno guardarAlumnoDeFormulario() {
		
		alumno.setNombre(editNombre.getText().toString());
		alumno.setSite(editSite.getText().toString());
		alumno.setTelefono(editTelefono.getText().toString());
		alumno.setDireccion(editDireccion.getText().toString());
		alumno.setNota(Double.valueOf(ratingNota.getRating()));
		
		return alumno;
		
	}

	public void colocaAlumnoEnFormulario(Alumno alumnoParaSerModificado) {
		alumno = alumnoParaSerModificado;
		editNombre.setText(alumnoParaSerModificado.getNombre());
		editSite.setText(alumnoParaSerModificado.getSite());
		editTelefono.setText(alumnoParaSerModificado.getTelefono());
		editDireccion.setText(alumnoParaSerModificado.getDireccion());
		ratingNota.setRating(alumnoParaSerModificado.getNota().floatValue());
	}

	public ImageView getFoto() {
		return foto;
	}

	public void cargarImagen(String caminoArchivo) {
		alumno.setFoto(caminoArchivo);
		Bitmap imagen = BitmapFactory.decodeFile(caminoArchivo);
		Bitmap imagenReducida = Bitmap.createScaledBitmap(imagen, 100,
				100, true);
		foto.setImageBitmap(imagenReducida);
	}


	
	

}
