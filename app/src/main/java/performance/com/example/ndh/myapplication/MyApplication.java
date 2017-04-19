package performance.com.example.ndh.myapplication;

import android.app.Application;

/**
 * Created by ndh on 17/3/8.
 */

public class MyApplication extends Application {
    private static final boolean DEV_MODE = true;

    @Override
    public void onCreate() {
        super.onCreate();
        }
}
