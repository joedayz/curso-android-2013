package pe.joedayz.registroalumnoslandscape.util;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import pe.joedayz.registroalumnoslandscape.modelo.Alumno;

public class AlumnoConverter {

	public String toJSON(List<Alumno> alumnos) {
		// TODO

		/*
		 * 
		 * { "list": [ { "aluno": [ { "nome": "Marta", "nota": 9 }, { "nome":
		 * "Maria", "nota": 7 } ] } ] }
		 */

		try {
			JSONStringer js = new JSONStringer();
			
			js.object().key("list").array();
			
			js.object().key("aluno").array();
			
			for (Alumno alumno : alumnos) {
				js.object();
				js.key("nome").value(alumno.getNombre());
				js.key("nota").value(alumno.getNota());
				js.endObject();
			}
			
			js.endArray().endObject();
			
			js.endArray().endObject();
			
			return js.toString();
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

}
