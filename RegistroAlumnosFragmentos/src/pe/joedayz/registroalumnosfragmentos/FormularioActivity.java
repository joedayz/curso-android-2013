package pe.joedayz.registroalumnosfragmentos;

import java.io.File;

import pe.joedayz.registroalumnosfragmentos.dao.AlumnoDAO;
import pe.joedayz.registroalumnosfragmentos.modelo.Alumno;
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
import android.widget.Toast;

public class FormularioActivity extends Activity {

	FormularioHelper helper;
	private String caminoArchivo;

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

		
		ImageView foto = helper.getFoto();
		foto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent irACamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				 caminoArchivo = Environment.getExternalStorageDirectory().toString() 
						   + "/" + System.currentTimeMillis() + ".png";
				File file = new File(caminoArchivo);
				Uri localFile =  Uri.fromFile(file);
				
				irACamara.putExtra(MediaStore.EXTRA_OUTPUT, localFile);
				
				startActivityForResult(irACamara, 123);
			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 123){
			if(resultCode == Activity.RESULT_OK){
				helper.cargarImagen(caminoArchivo);
			}else{
				caminoArchivo = null;
			}
		}
	}

}
