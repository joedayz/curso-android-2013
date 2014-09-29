package pe.joedayz.holamundo;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import pe.joedayz.holamundo.modelo.Alumno;

public class AlumnoConverter {

	public String toJSON(List<Alumno> alumnos) {
		// {"alumnos":[{"nombre":"jose","nota":17.0},
		// {"nombre":"miryan","nota":15.0},
		//{"nombre":"elias","nota":19}
		// ]
		// }

		try {
			JSONStringer js = new JSONStringer();
			js.object().key("alumnos").array();
			
			for (Alumno alumno : alumnos) {
				js.object();
				js.key("nombre").value(alumno.getNombre());
				js.key("nota").value(alumno.getNota());
				js.endObject();
			}
			
			js.endArray().endObject();
			return js.toString();
		} catch (JSONException ex) {
			throw new RuntimeException(ex);
		}
	}

}
