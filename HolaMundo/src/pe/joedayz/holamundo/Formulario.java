package pe.joedayz.holamundo;

import java.io.File;

import pe.joedayz.holamundo.dao.AlumnoDAO;
import pe.joedayz.holamundo.modelo.Alumno;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Formulario extends Activity {

	private Alumno alumnoSeleccionado;
	private String rutaArchivo;
	private FormularioHelper formularioHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		Intent intent = getIntent();
		alumnoSeleccionado = 
				(Alumno)intent.
				getSerializableExtra("alumnoSeleccionado");
		

		
		
		formularioHelper = new FormularioHelper(this);
		
		Button boton = (Button) findViewById(R.id.botonFormulario);
		
		if(alumnoSeleccionado!=null){
			boton.setText("Modificar");
			formularioHelper.
			colocarAlumnoEnFormulario(alumnoSeleccionado);
		}
		
		
		
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alumno alumno = formularioHelper.guardarAlumnoDeFormulario();
				
				AlumnoDAO dao = new AlumnoDAO(Formulario.this);
				
				if(alumnoSeleccionado == null){
					dao.guardar(alumno);
				}else{
					alumno.setId(alumnoSeleccionado.getId());
					dao.modificar(alumno);
				}
				
				
				
				dao.close();
				
				finish();
			}
		});
		

		//Tomar foto
		ImageView  foto = formularioHelper.getFoto();
		foto.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View v) {
				Intent irACamara = 
				  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				rutaArchivo = Environment.
						getExternalStorageDirectory().toString()
						+
						"/"
						+
						System.currentTimeMillis() + ".png";
				
				File file = new File(rutaArchivo);
				Uri localFile = Uri.fromFile(file);
				
				irACamara.putExtra(MediaStore.EXTRA_OUTPUT,
						localFile);
				startActivityForResult(irACamara, 123);
			}
		});
		
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, 
			int resultCode, Intent data) {
		if(requestCode == 123){
			
			if(resultCode == Activity.RESULT_OK){
				formularioHelper.cargarImagen(rutaArchivo);
			}else{
				rutaArchivo = null;
			}
					
		}
		
	}
	

}
