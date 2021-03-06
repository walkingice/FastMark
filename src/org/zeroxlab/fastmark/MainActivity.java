package org.zeroxlab.fastmark;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.preference.PreferenceManager;
import android.preference.EditTextPreference;

public class MainActivity extends Activity {

    private static int LENGTH = 4;
    private static String APP_ID = "BUILD_FOR_FAMILY";
    private static String KEY_BASE_URL = "key_base_url";
    private static String BASE_URL = "http://tw.stock.yahoo.com/q/q?s=";
    private String mBaseUrl;
    TextView mDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplay = (TextView)findViewById(R.id.displayText);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String url = prefs.getString(KEY_BASE_URL, BASE_URL);
        if (url != null || url != "") {
            mBaseUrl = url;
        } else {
            mBaseUrl = BASE_URL;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onClickClear(View v) {
        mDisplay.setText("");
    }

    public void onClickDone(View v) {
        String str = mDisplay.getText().toString();
        if (str.length() != LENGTH) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.warning_title);
            builder.setMessage(R.string.warning_msg_length_mismatch);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.warning_confirm, null);
            builder.show();
        } else {
            createShortcut(str);
        }
    }

    public void onClickNum(View v) {
        String tag = (String)v.getTag();
        if (tag != null) {
            appendNum(tag);
        }
    }

    private void createShortcut(String key) {
        final Intent in = new Intent();
        final Intent viewIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(mBaseUrl + key));
        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                this, R.drawable.ic_launcher);
        viewIntent.putExtra(Browser.EXTRA_APPLICATION_ID, APP_ID);
        in.putExtra(Intent.EXTRA_SHORTCUT_INTENT, viewIntent);
        in.putExtra(Intent.EXTRA_SHORTCUT_NAME, key);
        in.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        in.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        sendBroadcast(in);
        finish();
    }

    private void appendNum(String num) {
        String str = mDisplay.getText().toString();
        str = str + num;
        if (str.length() > LENGTH) {
            str = str.substring(str.length() - LENGTH);
        }

        mDisplay.setText(str);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            Intent intent = new Intent(this, PrefMain.class);
            startActivity(intent);
        }
        return true;
    }
}
