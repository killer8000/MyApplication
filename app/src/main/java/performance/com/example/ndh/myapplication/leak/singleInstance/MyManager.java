package performance.com.example.ndh.myapplication.leak.singleInstance;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ndh on 17/3/7.
 */

public class MyManager {
    private static Context mContext;
    private MyManager(){}
    public static MyManager createInstance(Context context){
        mContext=context;// 这里可能会有问题，如果传入的是Activity等
        return SingleTone.instance;
    }
    private static class SingleTone{
        public static final MyManager instance=new MyManager();
    }
    public void toast(String str){
        Toast.makeText(mContext,str,Toast.LENGTH_LONG).show();
    }
}
