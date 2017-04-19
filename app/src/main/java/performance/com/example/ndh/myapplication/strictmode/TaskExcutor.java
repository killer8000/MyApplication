package performance.com.example.ndh.myapplication.strictmode;

import android.os.StrictMode;
import android.os.SystemClock;

/**
 * Created by ndh on 17/3/8.
 */

public class TaskExcutor {
    private static long SLOW_CALL_THRESHOLD = 500;
    public void executeTask(Runnable task) {
        long startTime = SystemClock.uptimeMillis();
        task.run();
        long cost = SystemClock.uptimeMillis() - startTime;
        if (cost > SLOW_CALL_THRESHOLD) {
            StrictMode.noteSlowCall("slowCall cost=" + cost);
        }
    }
}
