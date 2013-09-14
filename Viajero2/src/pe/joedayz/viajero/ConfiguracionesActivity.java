package pe.joedayz.viajero;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConfiguracionesActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferencias);
	}
}
