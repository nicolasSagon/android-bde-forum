package android.bde_forum;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPreferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

	public void onBackPressed() {
		setResult(RESULT_OK);
		super.onBackPressed();
	}
}
