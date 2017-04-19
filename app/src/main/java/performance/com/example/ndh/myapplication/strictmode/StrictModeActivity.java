package performance.com.example.ndh.myapplication.strictmode;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import performance.com.example.ndh.myapplication.R;

import static android.content.ContentValues.TAG;

/**
 * Created by ndh on 17/3/8.
 */

public class StrictModeActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        //获取已有的规则
        StrictMode.ThreadPolicy old = StrictMode.getThreadPolicy();
        // 重写规则
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(old).permitDiskWrites().permitDiskReads().build());
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[]{permission}, 0);
            }
        writeToExternalStorage();
        setContentView(R.layout.activity_strict_mode);
        Button button = (Button) findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskExcutor excutor = new TaskExcutor();
                excutor.executeTask(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            Thread.sleep(800);
                            writeToExternalStorage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 文件系统的操作
     */
    public void writeToExternalStorage() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null || !externalStorageDirectory.exists()) {
            Log.d(TAG, "SDCard is not exist");
        } else {
            //获取SD卡下的文件或目录
            String[] childrenFiles = externalStorageDirectory.list();
            for (int i = 0; i < childrenFiles.length; i++) {
                File childFile = new File(externalStorageDirectory.getPath(), childrenFiles[i]);
                if (childFile.exists() && childFile.isFile()) {
                    Log.d("ndh--","file.name="+childFile.getName());
                    //找到第一个File
                    try {
                        //为了测试效果，此处不关fr
                        FileWriter fw = new FileWriter(childFile);
                        for (int p = 0; p < 1024  *10; p++) {
                            fw.write(p);
                            fw.flush();
                        }
                        //为了测试效果，此处不关fw

                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Toast.makeText(this,"done",Toast.LENGTH_LONG).show();
    }

}
