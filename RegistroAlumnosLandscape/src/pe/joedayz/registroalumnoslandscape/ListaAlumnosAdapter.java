package pe.joedayz.registroalumnoslandscape;

import java.util.List;

import pe.joedayz.registroalumnoslandscape.modelo.Alumno;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaAlumnosAdapter extends BaseAdapter {

	private List<Alumno> alumnos;
	private Activity activity;

	public ListaAlumnosAdapter(List<Alumno> alumnos, Activity activity) {
		this.alumnos = alumnos;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return alumnos.size();
	}

	@Override
	public Object getItem(int position) {
		return alumnos.get(position);
	}

	@Override
	public long getItemId(int position) {
		Alumno alumno = alumnos.get(position);
		return alumno.getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Alumno alumno = alumnos.get(position);

		LayoutInflater inflater = activity.getLayoutInflater();
		View linea = inflater.inflate(R.layout.linea_listade, null);

		TextView nombre = (TextView) linea.findViewById(R.id.nombre);
		nombre.setText(alumno.getNombre());

		ImageView foto = (ImageView) linea.findViewById(R.id.foto);

		if (alumno.getFoto() != null) {
			Bitmap fotoAlumno = BitmapFactory.decodeFile(alumno.getFoto());
			Bitmap fotoReducida = Bitmap.createScaledBitmap(fotoAlumno,
					100, 100, true);
			foto.setImageBitmap(fotoReducida);
		}else{
			Drawable sinFoto = activity.getResources().
					getDrawable(R.drawable.ic_no_imagen);
			foto.setImageDrawable(sinFoto );
		}
		return linea;
	}

}
