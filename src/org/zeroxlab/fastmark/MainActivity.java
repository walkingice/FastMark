package org.zeroxlab.fastmark;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView mDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplay = (TextView)findViewById(R.id.displayText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onClickNum(View v) {
        String tag = (String)v.getTag();
        if (tag != null) {
            prependNum(Integer.parseInt(tag));
        }
    }

    private void prependNum(int i) {
        mDisplay.setText(i + "");
    }
}
