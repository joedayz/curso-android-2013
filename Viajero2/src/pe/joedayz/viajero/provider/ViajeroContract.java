package pe.joedayz.viajero.provider;

import android.net.Uri;

public final class ViajeroContract {
	public static final String AUTHORITY = "pe.joedayz.viajero.provider";
	public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
	public static final String VIAJE_PATH = "viaje";
	public static final String GASTO_PATH = "gasto";
	
	public static final class Viaje{
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" +
						"vnd.pe.joedayz.viajero.provider/viaje";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" +
						"vnd.pe.joedayz.viajero.provider/viaje";

		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, VIAJE_PATH);
		public static final String _ID = "_ID";
		public static final String DESTINO = "DESTINO";
		public static final String FECHA_LLEGADA = "FECHA_LLEGADA";
		public static final String FECHA_SALIDA = "FECHA_SALIDA";
		public static final String PRESUPUESTO = "PRESUPUESTO";
		public static final String CANTIDAD_PERSONAS = "CANTIDAD_PERSONAS";
	}

	public static final class Gasto{
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" +
				"vnd.pe.joedayz.viajero.provider/gasto";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" +
				"vnd.pe.joedayz.viajero.provider/gasto";

		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, GASTO_PATH);
		public static final String _ID = "_ID";
		public static final String VIAJE_ID = "VIAJE_ID";
		public static final String CATEGORIA = "CATEGORIA";
		public static final String FECHA = "FECHA";
		public static final String DESCRIPCION = "DESCRIPCION";
		public static final String LOCAL = "LOCAL";
	}
}
