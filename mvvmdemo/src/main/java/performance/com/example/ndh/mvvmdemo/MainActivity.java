package performance.com.example.ndh.mvvmdemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import performance.com.example.ndh.mvvmdemo.databinding.ActivityMain1Binding;
import performance.com.example.ndh.mvvmdemo.presenter.news.MainPresenter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMain1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_main1);
        binding.setPresenter(new MainPresenter(binding,this));
    }

}
