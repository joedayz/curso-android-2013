package pe.joedayz.viajero;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String BANCO_DATOS = "Viajero";
	private static int VERSION = 1;
	
	public static class Viaje {
		public static final String TABLA = "VIAJE";
		public static final String _ID = "_ID";
		public static final String DESTINO = "DESTINO";
		public static final String FECHA_LLEGADA = "FECHA_LLEGADA";
		public static final String FECHA_SALIDA = "FECHA_SALIDA";
		public static final String PRESUPUESTO = "PRESUPUESTO";
		public static final String CANTIDAD_PERSONAS = "CANTIDAD_PERSONAS";
		public static final String TIPO_VIAJE = "TIPO_VIAJE";
		
		public static final String[] COLUMNAS = new String[]{
							_ID, DESTINO, FECHA_LLEGADA, FECHA_SALIDA,  
							TIPO_VIAJE, PRESUPUESTO, CANTIDAD_PERSONAS };
	}
	
	public static class Gasto{
		public static final String TABLA = "GASTO";
		public static final String _ID = "_ID";
		public static final String VIAJE_ID = "VIAJE_ID";
		public static final String CATEGORIA = "CATEGORIA";
		public static final String FECHA = "FECHA";
		public static final String DESCRIPCION = "DESCRIPCION";
		public static final String VALOR = "VALOR";
		public static final String LOCAL = "LOCAL";
		
		public static final String[] COLUMNAS = new String[]{
			_ID, VIAJE_ID, CATEGORIA, FECHA, DESCRIPCION, VALOR, LOCAL
		};
	}
	
	
	public DatabaseHelper(Context context) {
		super(context, BANCO_DATOS, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE VIAJE (_ID INTEGER PRIMARY KEY," +
					" DESTINO TEXT, TIPO_VIAJE INTEGER, FECHA_LLEGADA DATE," +
					" FECHA_SALIDA DATE, PRESUPUESTO DOUBLE, CANTIDAD_PERSONAS INTEGER);");
		
		db.execSQL("CREATE TABLE GASTO (_ID INTEGER PRIMARY KEY," +
					" CATEGORIA TEXT, FECHA DATE, VALOR DOUBLE, DESCRIPCION TEXT," +
					" LOCAL TEXT, VIAJE_ID INTEGER," +
					" FOREIGN KEY(VIAJE_ID) REFERENCES VIAJE(ID));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}