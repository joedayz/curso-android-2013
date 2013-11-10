package pe.joedayz.registroalumnoslandscape.task;

import java.util.List;

import pe.joedayz.registroalumnoslandscape.dao.AlumnoDAO;
import pe.joedayz.registroalumnoslandscape.modelo.Alumno;
import pe.joedayz.registroalumnoslandscape.util.AlumnoConverter;
import pe.joedayz.registroalumnoslandscape.util.WebClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class EnviaAlumnosTask extends AsyncTask<Integer, Double, String> {

	private Activity context;
	private ProgressDialog progress;

	public EnviaAlumnosTask(Activity context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Espere...",
				"Enviando datos a la web", true, true);
	}
	
	@Override
	protected String doInBackground(Integer... params) {

		// ACTIVIDAD PESADA
		String urlServer = "http://www.caelum.com.br/mobile";

		AlumnoDAO dao = new AlumnoDAO(context);
		List<Alumno> alumnos = dao.getLista();
		dao.close();

		String datosJSON = new AlumnoConverter().toJSON(alumnos);

		WebClient client = new WebClient(urlServer);

		final String respuestaJSON = client.post(datosJSON);

		return respuestaJSON;
	}

	@Override
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(context, result,
				Toast.LENGTH_LONG).show();

	}

}
