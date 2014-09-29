package pe.joedayz.holamundo.task;

import java.util.List;

import pe.joedayz.holamundo.AlumnoConverter;
import pe.joedayz.holamundo.WebClient;
import pe.joedayz.holamundo.dao.AlumnoDAO;
import pe.joedayz.holamundo.modelo.Alumno;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class EnviaAlumnosTask extends AsyncTask<Integer,
										Double, String>{

	
	private Activity context;
	private ProgressDialog progressDialog;


	public EnviaAlumnosTask(Activity context){
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
	
		progressDialog = ProgressDialog.show(context, 
				"Espere...", 
				"Enviando datos al servidor", true, true);
	
	}
	
	@Override
	protected String doInBackground(Integer... params) {
		//trabajo pesado en background
		
		String urlServidor = "http://android-mobile.joedayz.cloudbees.net/alumnos";
		
		AlumnoDAO dao = new AlumnoDAO(context);
		List<Alumno> alumnos = dao.getLista();
		dao.close();
		
		String datosJSON = new AlumnoConverter().toJSON(alumnos);
		
		WebClient client = new WebClient(urlServidor);
		String respuestaJSON = client.post(datosJSON);

		return respuestaJSON;
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressDialog.dismiss();
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
		
	}
	

}
