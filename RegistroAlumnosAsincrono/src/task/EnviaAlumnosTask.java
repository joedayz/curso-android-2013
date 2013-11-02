package task;

import java.util.List;

import pe.joedayz.registroalumnosasincrono.ListaAlumnosActivity;
import pe.joedayz.registroalumnosasincrono.dao.AlumnoDAO;
import pe.joedayz.registroalumnosasincrono.modelo.Alumno;
import pe.joedayz.registroalumnosasincrono.util.AlumnoConverter;
import pe.joedayz.registroalumnosasincrono.util.WebClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class EnviaAlumnosTask extends AsyncTask<Integer, Double, String> {

	private Activity context;

	public EnviaAlumnosTask(Activity context) {
		this.context = context;
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

		Toast.makeText(context, result,
				Toast.LENGTH_LONG).show();

	}

}
