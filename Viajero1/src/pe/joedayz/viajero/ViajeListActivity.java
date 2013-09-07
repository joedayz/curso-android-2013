package pe.joedayz.viajero;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;

public class ViajeListActivity extends ListActivity implements
		OnItemClickListener, OnClickListener, ViewBinder {

	private List<Map<String, Object>> viajes;
	private AlertDialog alertDialog;
	private int viajeSeleccionado;
	private AlertDialog dialogConfirmacion;
	private boolean modoSeleccionarViaje;

	private DatabaseHelper helper;
	private SimpleDateFormat dateFormat;
	private Double valorLimite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		helper = new DatabaseHelper(this);
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		SharedPreferences preferencias = PreferenceManager
				.getDefaultSharedPreferences(this);

		String valor = preferencias.getString("valor_limite", "-1");
		valorLimite = Double.valueOf(valor);

		// código adicional
		getListView().setOnItemClickListener(this);
		alertDialog = crearAlertDialog();
		dialogConfirmacion = crearDialogConfirmacion();

		if (getIntent().hasExtra(Constantes.MODO_SELECCIONAR_VIAJE)) {
			modoSeleccionarViaje = getIntent().getExtras().getBoolean(
					Constantes.MODO_SELECCIONAR_VIAJE);
		}
	}

	private List<Map<String, Object>> listarViajes() {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT _ID, TIPO_VIAJE, DESTINO, "
				+ "FECHA_LLEGADA, FECHA_SALIDA, PRESUPUESTO FROM VIAJE", null);

		cursor.moveToFirst();

		viajes = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < cursor.getCount(); i++) {

			Map<String, Object> item = new HashMap<String, Object>();

			String id = cursor.getString(0);
			int tipoViaje = cursor.getInt(1);
			String destino = cursor.getString(2);
			long viajeLlegada = cursor.getLong(3);
			long viajeSalida = cursor.getLong(4);
			double presupuesto = cursor.getDouble(5);

			item.put("id", id);

			if (tipoViaje == Constantes.VIAJE_PLACER) {
				item.put("imagen", R.drawable.lazer);
			} else {
				item.put("imagen", R.drawable.negocios);
			}

			item.put("destino", destino);

			Date fechaLlegadaDate = new Date(viajeLlegada);
			Date fechaSalidaDate = new Date(viajeSalida);

			String periodo = dateFormat.format(fechaLlegadaDate) + " a "
					+ dateFormat.format(fechaSalidaDate);

			item.put("fecha", periodo);

			double totalGasto = calcularTotalGasto(db, id);

			item.put("total", "Gasto total S/. " + totalGasto);

			double alerta = presupuesto * valorLimite / 100;
			// Double[] valores = new Double[] { presupuesto, alerta, totalGasto
			// };
			// item.put("barraProgresso", valores);
			viajes.add(item);

			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return viajes;
	}

	private double calcularTotalGasto(SQLiteDatabase db, String id) {
		Cursor cursor = db.rawQuery(
				"SELECT SUM(VALOR) FROM GASTO WHERE VIAJE_ID = ?",
				new String[] { id });
		cursor.moveToFirst();
		double total = cursor.getDouble(0);
		cursor.close();
		return total;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (modoSeleccionarViaje) {
			String destino = (String) viajes.get(position).get("destino");
			int idViagem = 1;

			Intent data = new Intent();
			data.putExtra(Constantes.VIAJE_ID, idViagem);
			data.putExtra(Constantes.VIAJE_DESTINO, destino);
			setResult(Activity.RESULT_OK, data);
			finish();
		} else {
			viajeSeleccionado = position;
			alertDialog.show();
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int item) {

		Intent intent;
		String id = (String) viajes.get(viajeSeleccionado).get("id");
		switch (item) {
		case 0: // editar viaje
			intent = new Intent(this, ViajeActivity.class);
			intent.putExtra(Constantes.VIAJE_ID, id);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, GastoActivity.class);
			intent.putExtra(Constantes.VIAJE_ID, 1);
			intent.putExtra(Constantes.VIAJE_DESTINO,
					viajes.get(viajeSeleccionado).get("destino").toString());
			startActivity(intent);
			break;
		case 2:
			startActivity(new Intent(this, GastoListActivity.class));
			break;
		case 3:
			dialogConfirmacion.show();
			break;
		case DialogInterface.BUTTON_POSITIVE:
			viajes.remove(viajeSeleccionado);
			eliminarViaje(id);
			getListView().invalidateViews();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			dialogConfirmacion.dismiss();
			break;
		}
	}
	
	private void eliminarViaje(String id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		String where[] = new String[] { id };
		db.delete("GASTO", "VIAJE_ID = ?", where);
		db.delete("VIAJE", "_ID = ?", where);
	}

	private AlertDialog crearAlertDialog() {
		final CharSequence[] items = { getString(R.string.editar),
				getString(R.string.nuevo_gasto),
				getString(R.string.gastos_realizados),
				getString(R.string.eliminar) };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.opciones);
		builder.setItems(items, this);

		return builder.create();
	}

	private AlertDialog crearDialogConfirmacion() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.confirmacion_eliminacion_viaje);
		builder.setPositiveButton(getString(R.string.si), this);
		builder.setNegativeButton(getString(R.string.no), this);

		return builder.create();
	}

	public boolean setViewValue(View view, Object data,
			String textRepresentation) {

		return false;
	}

}
