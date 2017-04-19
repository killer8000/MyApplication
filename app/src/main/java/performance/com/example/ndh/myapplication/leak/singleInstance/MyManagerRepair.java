package performance.com.example.ndh.myapplication.leak.singleInstance;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by ndh on 17/3/7.
 */

public class MyManagerRepair {
//    private static Context mContext;
    private static WeakReference<Context> mContext;
    private MyManagerRepair() {
    }

    public static MyManagerRepair createInstance(Context context) {
//        mContext=context;
        mContext = new WeakReference<Context>(context);// 这里可能会有问题，如果传入的是Activity等
        return SingleTone.instance;
    }

    private static class SingleTone {
        public static final MyManagerRepair instance = new MyManagerRepair();
    }

    public void toast(String str) {
        Toast.makeText(mContext.get(), str, Toast.LENGTH_LONG).show();
    }
}
