package org.zeroxlab.fastmark;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PrefMain extends PreferenceActivity {

    public final static String KEY_BASE_URL = "key_base_url";
    public final static String KEY_RESET_URL = "key_reset_url";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_main);
        Preference resetPref = findPreference(KEY_RESET_URL);

        OnPreferenceClickListener listener = new PrefListener();
        resetPref.setOnPreferenceClickListener(listener);
    }

    private void resetBaseUrl() {
        Preference urlPref = findPreference(KEY_BASE_URL);
        SharedPreferences.Editor editor = urlPref.getEditor();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    class PrefListener implements OnPreferenceClickListener {
        public boolean onPreferenceClick(Preference preference) {
            if (preference.getKey().equals(KEY_RESET_URL)) {
                resetBaseUrl();
            }

            return true;
        }
    }
}
