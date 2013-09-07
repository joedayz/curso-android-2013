package pe.joedayz.viajero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

public class ViajeListActivity extends ListActivity implements
		OnItemClickListener, OnClickListener, ViewBinder {

	private List<Map<String, Object>> viajes;
	private AlertDialog alertDialog;
	private int viajeSeleccionado;
	private AlertDialog dialogConfirmacion;
	private boolean modoSeleccionarViaje;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		String[] de = { "imagen", "destino", "fecha", "total", "barraProgreso" };
		int[] para = { R.id.tipoViaje, R.id.destino, R.id.fecha, R.id.valor ,
				 R.id.barraProgresso};

		SimpleAdapter adapter = new SimpleAdapter(this, listarViajes(),
				R.layout.lista_viaje, de, para);

		adapter.setViewBinder(this);

		setListAdapter(adapter);

		getListView().setOnItemClickListener(this);
		getListView().setOnItemClickListener(this);
		alertDialog = crearAlertDialog();
		dialogConfirmacion = crearDialogConfirmacion();
		
		 if(getIntent().hasExtra(PrincipalActivity.MODO_SELECCIONAR_VIAJE)){
			 modoSeleccionarViaje = getIntent().getExtras()
					 				 .getBoolean(PrincipalActivity.MODO_SELECCIONAR_VIAJE);
		 }
		
	}

	private List<Map<String, Object>> listarViajes() {
		viajes = new ArrayList<Map<String, Object>>();

		Map<String, Object> item = new HashMap<String, Object>();
		item.put("imagen", R.drawable.negocios);
		item.put("destino", "Lima");
		item.put("fecha", "02/02/2012 a 04/02/2012");
		item.put("total", "Gasto total S/. 314,98");
		item.put("barraProgresso", new Double[]{ 500.0, 450.0, 314.98});
		viajes.add(item);

		item = new HashMap<String, Object>();
		item.put("imagen", R.drawable.lazer);
		item.put("destino", "Uruguay");
		item.put("fecha", "14/05/2012 a 22/05/2012");
		item.put("total", "Gasto total S/. 25834,67");
		item.put("barraProgresso", new Double[]{ 30000.0, 28600.0, 25834.67 });
		viajes.add(item);

		return viajes;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(modoSeleccionarViaje){
			String destino = (String) viajes.get(position).get("destino");
			int idViagem = 1;

			Intent data = new Intent();
			data.putExtra(PrincipalActivity.VIAJE_ID, idViagem);
			data.putExtra(PrincipalActivity.VIAJE_DESTINO, destino);
			setResult(Activity.RESULT_OK, data );
			finish();
		}else{
			viajeSeleccionado = position;
			alertDialog.show();
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int item) {
		switch (item) {
		case 0:
			startActivity(new Intent(this, ViajeActivity.class));
			break;
		case 1:
			Intent intent = new Intent(this, GastoActivity.class);
			intent.putExtra(PrincipalActivity.VIAJE_ID, 1);
			intent.putExtra(PrincipalActivity.VIAJE_DESTINO, viajes.get(viajeSeleccionado).
					get("destino").toString());
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
	        getListView().invalidateViews();
	        break;
	    case DialogInterface.BUTTON_NEGATIVE:
	        dialogConfirmacion.dismiss();
	        break;
	}
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
	    if (view.getId() == R.id.barraProgresso) {
	        Double valores[] = (Double[]) data;
	        ProgressBar progressBar = (ProgressBar) view;
	        progressBar.setMax(valores[0].intValue());
	        progressBar.setSecondaryProgress(valores[1].intValue());
	        progressBar.setProgress(valores[2].intValue());
	        return true;
	    }
	    return false;
	}
	
}
