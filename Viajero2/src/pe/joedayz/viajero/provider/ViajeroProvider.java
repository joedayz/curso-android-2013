package pe.joedayz.viajero.provider;

import pe.joedayz.viajero.DatabaseHelper;
import pe.joedayz.viajero.provider.ViajeroContract.Gasto;
import pe.joedayz.viajero.provider.ViajeroContract.Viaje;
import static pe.joedayz.viajero.provider.ViajeroContract.AUTHORITY;
import static pe.joedayz.viajero.provider.ViajeroContract.GASTO_PATH;
import static pe.joedayz.viajero.provider.ViajeroContract.VIAJE_PATH;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ViajeroProvider extends ContentProvider{

private DatabaseHelper helper;
	
	private static final int VIAJES = 1;
	private static final int VIAJE_ID = 2;
	private static final int GASTOS = 3;
	private static final int GASTO_ID = 4;
	private static final int GASTOS_VIAJE_ID = 5;
	
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static{
		uriMatcher.addURI(AUTHORITY, 
						  VIAJE_PATH, 
						  VIAJES);

		uriMatcher.addURI(AUTHORITY, 
						  VIAJE_PATH + "/#", 
						  VIAJE_ID);
						  
		uriMatcher.addURI(AUTHORITY, 
						  GASTO_PATH, 
						  GASTOS);
						  
		uriMatcher.addURI(AUTHORITY, 
						  GASTO_PATH + "/#", 
						  GASTO_ID);
						  
		uriMatcher.addURI(AUTHORITY, 
						  GASTO_PATH + "/"+ VIAJE_PATH + "/#", 
						  GASTOS_VIAJE_ID);
	}
	
	@Override
	public boolean onCreate() {
		helper = new DatabaseHelper(getContext());
		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteDatabase database = helper.getWritableDatabase();
		
		switch (uriMatcher.match(uri)) {
		
		case VIAJES:
			
			return database.query(VIAJE_PATH, projection, 
							selection, selectionArgs, null, null, sortOrder);
			
		case VIAJE_ID:
			
			selection = Viaje._ID + " = ?";
			selectionArgs = new String[] {uri.getLastPathSegment()};
			return database.query(VIAJE_PATH, projection, 
					selection, selectionArgs, null, null, sortOrder);
			
		case GASTOS:
			
			return database.query(GASTO_PATH, projection, 
					selection, selectionArgs, null, null, sortOrder);
			
		case GASTO_ID:
			
			selection = Gasto._ID + " = ?";
			selectionArgs = new String[] {uri.getLastPathSegment()};
			return database.query(GASTO_PATH, projection, 
					selection, selectionArgs, null, null, sortOrder);
			
		case GASTOS_VIAJE_ID:
			
			selection = Gasto.VIAJE_ID + " = ?";
			selectionArgs = new String[] {uri.getLastPathSegment()};
			return database.query(GASTO_PATH, projection, 
					selection, selectionArgs, null, null, sortOrder);
			
		default:
			throw new IllegalArgumentException("Uri desconhecida");
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase database = helper.getWritableDatabase();
		long id;

		switch (uriMatcher.match(uri)) {
		
		case VIAJES:
			id = database.insert(VIAJE_PATH, null, values);
			return Uri.withAppendedPath(Viaje.CONTENT_URI, 
										String.valueOf(id));
			
		case GASTOS:
			id = database.insert(GASTO_PATH, null, values);
			return Uri.withAppendedPath(Gasto.CONTENT_URI, 
										String.valueOf(id));
		default:
			throw new IllegalArgumentException("Uri desconhecida");
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		SQLiteDatabase database = helper.getWritableDatabase();
		
		switch (uriMatcher.match(uri)) {
		
		case VIAJE_ID:
			
			selection = Viaje._ID + " = ?";
			selectionArgs = new String[] {uri.getLastPathSegment()};
			return database.delete(VIAJE_PATH, 
								   selection, selectionArgs);
		
		case GASTO_ID:
			
			selection = Gasto._ID + " = ?";
			selectionArgs = new String[] {uri.getLastPathSegment()};
			return database.delete(GASTO_PATH, 
								   selection, selectionArgs);
			
		default:
			throw new IllegalArgumentException("Uri desconhecida");
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, 
					  String selection, String[] selectionArgs) {
		
		SQLiteDatabase database = helper.getWritableDatabase();
		
		switch (uriMatcher.match(uri)) {
		
		case VIAJE_ID:
			
			selection = Viaje._ID + " = ?";
			selectionArgs = new String[] {uri.getLastPathSegment()};
			return database.update(VIAJE_PATH, values, 
								   selection, selectionArgs);
		
		case GASTO_ID:
			
			selection = Gasto._ID + " = ?";
			selectionArgs = new String[] {uri.getLastPathSegment()};
			return database.update(GASTO_PATH, values, 
								   selection, selectionArgs);
		
		default:
			throw new IllegalArgumentException("Uri desconhecida");
		}
	}
	
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		
		case VIAJES:
			
			return Viaje.CONTENT_TYPE;
			
		case VIAJE_ID:
			
			return Viaje.CONTENT_ITEM_TYPE;

		case GASTOS:
		case GASTOS_VIAJE_ID:
			
			return Gasto.CONTENT_TYPE;
			
		case GASTO_ID:
			
			return Gasto.CONTENT_ITEM_TYPE;
			
		default:
			throw new IllegalArgumentException("Uri desconocido");
		}
	}
}
