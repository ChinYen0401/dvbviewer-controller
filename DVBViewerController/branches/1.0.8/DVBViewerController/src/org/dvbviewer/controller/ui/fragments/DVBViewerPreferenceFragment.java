package org.dvbviewer.controller.ui.fragments;

import org.dvbviewer.controller.R;
import org.dvbviewer.controller.entities.DVBViewerPreferences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;
import android.support.v4.preference.PreferenceFragment;

public class DVBViewerPreferenceFragment extends  PreferenceFragment implements OnPreferenceClickListener{
	
	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName(DVBViewerPreferences.PREFS);

		addPreferencesFromResource(R.xml.preferences);
		Preference rsSettings = (Preference) findPreference(DVBViewerPreferences.KEY_RS_SETTINGS);
		rsSettings.setOnPreferenceClickListener(this);
		Preference dvbvSettings = (Preference) findPreference(DVBViewerPreferences.KEY_DVBV_SETTINGS);
		dvbvSettings.setOnPreferenceClickListener(this);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if (preference.getKey().equals(DVBViewerPreferences.KEY_RS_SETTINGS)) {
			preference.getIntent().putExtra("id", R.xml.rs_preferences);
		}else if (preference.getKey().equals(DVBViewerPreferences.KEY_DVBV_SETTINGS)) {
			preference.getIntent().putExtra("id", R.xml.dvbv_preferences);
		}
		return false;
	}

	

}
