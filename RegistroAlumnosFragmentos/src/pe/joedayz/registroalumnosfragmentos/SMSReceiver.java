package pe.joedayz.registroalumnosfragmentos;

import pe.joedayz.registroalumnosfragmentos.dao.AlumnoDAO;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Object[] mensajes = (Object[]) intent.getExtras().get("pdus");
		byte[] primera = (byte[]) mensajes[0];
		SmsMessage sms = SmsMessage.createFromPdu(primera);
		
		String telefono = sms.getDisplayOriginatingAddress();
		//primero pruebo que funciona en el emulador
//		Toast.makeText(context, "sms del telefono " + telefono, 
//				Toast.LENGTH_LONG).show();
		
	
		AlumnoDAO dao = new AlumnoDAO(context);
		boolean esAlumno = dao.isAlumno(telefono);
		
		if(esAlumno){
			MediaPlayer player = MediaPlayer.create(context, R.raw.main);
			player.start();
			Toast.makeText(context, "Tocando musica...",
					Toast.LENGTH_LONG).show();
		}
		
		dao.close();
	}

}
