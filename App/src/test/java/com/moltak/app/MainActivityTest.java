package com.moltak.app;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static junit.framework.Assert.*;

/**
 * Created by moltak on 12/2/13.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    @Test
    public void testInitialize() {
        // activity get
        FragmentActivity a = Robolectric.buildActivity(MainActivity.class).create().start().resume().get();

        // fragment not check
        Fragment f1 = a.getSupportFragmentManager().findFragmentByTag("placeholderFragment");
        assertNotNull(f1);

        // resource check
        Resources r = a.getResources();
        String s = r.getString(R.string.hello_world);

        TextView textView = (TextView)f1.getView().findViewById(R.id.textview);
        String ts = textView.getText().toString();
        assertEquals(s, ts);
    }
}
