package pe.joedayz.holamundo;

import pe.joedayz.holamundo.modelo.Alumno;
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
	
	public FormularioHelper(Formulario formulario) {
		 alumno = new Alumno();

		 editNombre = (EditText) formulario.findViewById(R.id.edtNombre);
		 editSite = (EditText) formulario.findViewById(R.id.edtSite);
		 editTelefono = (EditText) formulario.findViewById(R.id.edtTelefono);
		 editDireccion = (EditText) formulario.findViewById(R.id.edtDireccion);

		 ratingNota = (RatingBar) formulario.findViewById(R.id.nota);

		 foto =  (ImageView) formulario.findViewById(R.id.fotoAlumno);

	}

	public ImageView getFoto(){
		return foto;
	}
	
	public Alumno guardarAlumnoDeFormulario() {
		alumno.setNombre(editNombre.getText().toString());
		alumno.setSite(editSite.getText().toString());
		alumno.setDireccion(editDireccion.getText().toString());
		alumno.setTelefono(editTelefono.getText().toString());
		alumno.setNota(Double.valueOf(ratingNota.getRating()));
		
		return alumno;
	}

	public void colocarAlumnoEnFormulario(Alumno alumnoSeleccionado) {
		alumno = alumnoSeleccionado;
		editNombre.setText(alumnoSeleccionado.getNombre());
		editSite.setText(alumnoSeleccionado.getSite());
		editDireccion.setText(alumnoSeleccionado.getDireccion());
		editTelefono.setText(alumnoSeleccionado.getTelefono());
		ratingNota.setRating(alumnoSeleccionado.getNota().
					floatValue());
	}
	
	
	public void cargarImagen(String caminoArchivo) {
		alumno.setFoto(caminoArchivo);
		Bitmap imagen = BitmapFactory.decodeFile(caminoArchivo);
		Bitmap imagenReducida = Bitmap.createScaledBitmap(imagen,
				100,
				100, 
				true);
		foto.setImageBitmap(imagenReducida);
	}

}
