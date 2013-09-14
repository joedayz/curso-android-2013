package pe.joedayz.viajero.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.joedayz.viajero.DatabaseHelper;
import pe.joedayz.viajero.domain.Gasto;
import pe.joedayz.viajero.domain.Viaje;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ViajeroDAO {

	
	private DatabaseHelper helper;

	private SQLiteDatabase db;
	
	public ViajeroDAO(Context context){
		helper = new DatabaseHelper(context);
	}

	private SQLiteDatabase getDb() {
		if (db == null) {
			db = helper.getWritableDatabase();
		}
		return db;
	}
	
	public void close(){
		helper.close();
	}
	
	public List<Viaje> listarViagens(){
		Cursor cursor = getDb().query(DatabaseHelper.Viaje.TABLA, 
									  DatabaseHelper.Viaje.COLUMNAS, 
									  null, null, null, null, null);
		List<Viaje> viajes = new ArrayList<Viaje>();
		while(cursor.moveToNext()){
			Viaje viaje = crearViaje(cursor);
			viajes.add(viaje);
		}
		cursor.close();
		return viajes;
	}

	public Viaje buscarViajePorId(Integer id){
		Cursor cursor = getDb().query(DatabaseHelper.Viaje.TABLA, 
									  DatabaseHelper.Viaje.COLUMNAS, 
									  DatabaseHelper.Viaje._ID + " = ?", 
									  new String[]{ id.toString() }, 
									  null, null, null);
		if(cursor.moveToNext()){
			Viaje viaje = crearViaje(cursor);
			cursor.close();
			return viaje;
		}
		
		return null;
	}
	
	public long agregarViaje(Viaje viaje){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Viaje.DESTINO,
				   viaje.getDestino());
		
		values.put(DatabaseHelper.Viaje.TIPO_VIAJE,
				   viaje.getTipoViagem());
		
		values.put(DatabaseHelper.Viaje.FECHA_LLEGADA, 
				   viaje.getDataChegada().getTime());
		
		values.put(DatabaseHelper.Viaje.FECHA_SALIDA, 
				   viaje.getDataSaida().getTime());
		
		values.put(DatabaseHelper.Viaje.PRESUPUESTO, 
				   viaje.getOrcamento());
		
		values.put(DatabaseHelper.Viaje.CANTIDAD_PERSONAS, 
				   viaje.getQuantidadePessoas());
		
		return getDb().insert(DatabaseHelper.Viaje.TABLA, 
							 null, values);
	}
	
	public boolean eliminarViaje(Long id){
		String whereClause = DatabaseHelper.Viaje._ID + " = ?";
		String[] whereArgs = new String[]{id.toString()};
		int removidos = getDb().delete(DatabaseHelper.Viaje.TABLA, 
									   whereClause, whereArgs);
		return removidos > 0;
	}
	
	public List<Gasto> listarGastos(Viaje viagem){
		String selection = DatabaseHelper.Gasto.VIAJE_ID + " = ?";
		String[] selectionArgs = new String[]{viagem.getId().toString()};
		
		Cursor cursor = getDb().query(DatabaseHelper.Gasto.TABLA, 
									  DatabaseHelper.Gasto.COLUMNAS, 
									  selection, selectionArgs, 
									  null, null, null);
		List<Gasto> gastos = new ArrayList<Gasto>();
		while(cursor.moveToNext()){
			Gasto gasto = crearGasto(cursor);
			gastos.add(gasto);
		}
		cursor.close();
		return gastos;
	}

	public Gasto buscarGastoPorId(Integer id){
		Cursor cursor = getDb().query(DatabaseHelper.Gasto.TABLA, 
									  DatabaseHelper.Gasto.COLUMNAS, 
									  DatabaseHelper.Gasto._ID + " = ?", 
									  new String[]{ id.toString() }, 
									  null, null, null);
		if(cursor.moveToNext()){
			Gasto gasto = crearGasto(cursor);
			cursor.close();
			return gasto;
		}
		return null;
	}
	
	public long agregarGasto(Gasto gasto){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Gasto.CATEGORIA, 
				   gasto.getCategoria());
		
		values.put(DatabaseHelper.Gasto.FECHA, 
				   gasto.getFecha().getTime());
		
		values.put(DatabaseHelper.Gasto.DESCRIPCION, 
				   gasto.getDescripcion());
		
		values.put(DatabaseHelper.Gasto.LOCAL, 
				   gasto.getLocal());
		
		values.put(DatabaseHelper.Gasto.VALOR, 
				   gasto.getValor());
		
		values.put(DatabaseHelper.Gasto.VIAJE_ID, 
				   gasto.getViajeId());
		
		return getDb().insert(DatabaseHelper.Gasto.TABLA, 
							  null, values);
	}
	
	public boolean eliminarGasto(Long id){
		String whereClause = DatabaseHelper.Gasto._ID + " = ?";
		String[] whereArgs = new String[]{id.toString()};
		int removidos = getDb().delete(DatabaseHelper.Gasto.TABLA, 
									   whereClause, whereArgs);
		return removidos > 0;
	}
	
	public double calcularTotalGasto(Viaje viagem){
		Cursor cursor = getDb().rawQuery(
					"SELECT SUM("+DatabaseHelper.Gasto.VALOR + ") FROM " +
							DatabaseHelper.Gasto.TABLA + " WHERE " + 
							DatabaseHelper.Gasto.VIAJE_ID + " = ?", 
							new String[]{ viagem.getId().toString() });
		cursor.moveToFirst();
		double total = cursor.getDouble(0);
		cursor.close();
		return total;
	}
	
	private Viaje crearViaje(Cursor cursor) {
		Viaje viagem = new Viaje(
				
			cursor.getInt(cursor.getColumnIndex(
								 DatabaseHelper.Viaje._ID)),
								 
			cursor.getString(cursor.getColumnIndex(
								 DatabaseHelper.Viaje.DESTINO)),
								 
			cursor.getInt(cursor.getColumnIndex(
								 DatabaseHelper.Viaje.TIPO_VIAJE)),
								 
			new Date(cursor.getLong(cursor.getColumnIndex(
								 DatabaseHelper.Viaje.FECHA_LLEGADA))),
								 
			new Date(cursor.getLong(cursor.getColumnIndex(
								 DatabaseHelper.Viaje.FECHA_SALIDA))),
								 
			cursor.getDouble(cursor.getColumnIndex(
								 DatabaseHelper.Viaje.PRESUPUESTO)),
								 
			cursor.getInt(cursor.getColumnIndex(
								 DatabaseHelper.Viaje.CANTIDAD_PERSONAS))
		);
		return viagem;
	}
	
	private Gasto crearGasto(Cursor cursor) {
		Gasto gasto = new Gasto(
				cursor.getInt(cursor.getColumnIndex(
									 DatabaseHelper.Gasto._ID)),
									 
				new Date(cursor.getLong(cursor.getColumnIndex(
										DatabaseHelper.Gasto.FECHA))),
										
				cursor.getString(cursor.getColumnIndex(
										DatabaseHelper.Gasto.CATEGORIA)),
										
				cursor.getString(cursor.getColumnIndex(
										DatabaseHelper.Gasto.DESCRIPCION)),
										
				cursor.getDouble(cursor.getColumnIndex(
										DatabaseHelper.Gasto.VALOR)),
										
				cursor.getString(cursor.getColumnIndex(
										DatabaseHelper.Gasto.LOCAL)),
										
				cursor.getInt(cursor.getColumnIndex(
										DatabaseHelper.Gasto.VIAJE_ID))
		);
		return gasto;
	}
}
