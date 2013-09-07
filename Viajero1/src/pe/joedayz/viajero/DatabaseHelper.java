package pe.joedayz.viajero;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String BASE_DATOS = "Viajero";
	private static int VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, BASE_DATOS, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE VIAJE (_ID INTEGER PRIMARY KEY,"
				+ " DESTINO TEXT, TIPO_VIAJE INTEGER, FECHA_LLEGADA DATE,"
				+ " FECHA_SALIDA DATE, PRESUPUESTO DOUBLE, CANTIDAD_PERSONAS INTEGER);");

		db.execSQL("CREATE TABLE GASTO (_ID INTEGER PRIMARY KEY,"
				+ " CATEGORIA TEXT, FECHA DATE, VALOR DOUBLE, DESCRIPCION TEXT,"
				+ " LOCAL TEXT, VIAJE_ID INTEGER,"
				+ " FOREIGN KEY(VIAJE_ID) REFERENCES VIAJE(_ID));");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
