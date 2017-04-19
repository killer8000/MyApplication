package performance.com.example.ndh.myapplication.leak.singleInstance;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import performance.com.example.ndh.myapplication.R;

/**
 * Created by ndh on 17/3/7.
 */

public class SingleInstanceLeak extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        MyManager.createInstance(this).toast("single instance leak");
//        MyManagerRepair.createInstance(this).toast("single instance leak");
    }
}
