package performance.com.example.ndh.mvpdemo.presenter.news;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by ndh on 17/4/14.
 * 用户交互接口
 */

public interface MainPresenter {
    void onItemClick(AdapterView<?> parent, View view, int position, long id);
    void onClick(View v);
}
