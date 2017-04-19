package performance.com.example.ndh.myapplication.leak.handleleak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import performance.com.example.ndh.myapplication.R;

/**
 * Created by ndh on 17/3/7.
 */

public class HandleLeak extends Activity {
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);
        initHandle();

    }

    private void initHandle() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(final Message msg) {
                switch (msg.what) {
                    case 0:
                        break;
                    case 1:
                    case 2:
                }
            }
        };
    }
}
