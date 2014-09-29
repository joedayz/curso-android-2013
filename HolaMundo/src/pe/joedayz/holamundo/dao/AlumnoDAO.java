package pe.joedayz.holamundo.dao;

import java.util.ArrayList;
import java.util.List;

import pe.joedayz.holamundo.modelo.Alumno;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlumnoDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "regAlumnos";
	private static final int VERSION = 2;

	public AlumnoDAO(Context context) {
		super(context, DATABASE, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	public void guardar(Alumno alumno) {
		ContentValues values = new ContentValues();
		values.put("nombre", alumno.getNombre());
		values.put("site", alumno.getSite());
		values.put("direccion", alumno.getDireccion());
		values.put("nota", alumno.getNota());
		values.put("foto", alumno.getFoto());
		values.put("telefono", alumno.getTelefono());
		getWritableDatabase().insert("Alumno", null, values);

	}

	//
	// private Long id;
	// private String nombre;
	// private String site;
	// private String direccion;
	// private String telefono;
	// private Double nota;
	// private String foto;

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE Alumno (id INTEGER PRIMARY KEY, "
				+ "nombre TEXT UNIQUE NOT NULL," + "telefono TEXT, "
				+ "direccion TEXT," + "site TEXT," + "foto TEXT,"
				+ "nota REAL);";
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String ddl = "DROP TABLE IF EXISTS Alumno";
		db.execSQL(ddl);

		this.onCreate(db);

	}

	public List<Alumno> getLista() {
		String[] columnas = { "id", "nombre", "site", "telefono", "direccion",
				"foto", "nota" };
		Cursor cursor = getWritableDatabase().query("Alumno", columnas, null,
				null, null, null, null, null);
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		while (cursor.moveToNext()) {
			Alumno alumno = new Alumno();
			alumno.setId(cursor.getLong(0));
			alumno.setNombre(cursor.getString(1));
			alumno.setSite(cursor.getString(2));
			alumno.setTelefono(cursor.getString(3));
			alumno.setDireccion(cursor.getString(4));
			alumno.setFoto(cursor.getString(5));
			alumno.setNota(cursor.getDouble(6));
			alumnos.add(alumno);
		}

		return alumnos;
	}

	public void eliminar(Alumno alumno) {
		String[] args = {alumno.getId().toString()};
		
		getWritableDatabase().delete("Alumno", "id=?", args);
	}

	public void modificar(Alumno alumno) {
		ContentValues values = new ContentValues();
		values.put("nombre", alumno.getNombre());
		values.put("site", alumno.getSite());
		values.put("direccion", alumno.getDireccion());
		values.put("nota", alumno.getNota());
		values.put("foto", alumno.getFoto());
		values.put("telefono", alumno.getTelefono());
		
		String[] args = {alumno.getId().toString()};
		
		getWritableDatabase().update("Alumno", values, 
				"id=?", args);

		
	}

	public boolean isAlumno(String telefono) {
		String[] args = {telefono};
		Cursor cursor = getWritableDatabase().
          rawQuery("SELECT id from Alumno" +
          		" WHERE telefono=?", args);
		
		boolean existePrimero = cursor.moveToFirst();
		return existePrimero;
	}

}
