package performance.com.example.ndh.rxandroiddemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import performance.com.example.ndh.rxandroiddemo.adapter.NewsAdapter;
import performance.com.example.ndh.rxandroiddemo.net.NetApi;
import performance.com.example.ndh.rxandroiddemo.net.RetrofitManager;
import performance.com.example.ndh.rxandroiddemo.realm.NetResult;
import performance.com.example.ndh.rxandroiddemo.realm.NewsBean;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private ProgressBar mProgressBar;
    private ListView mListView;
    private List<NewsBean> mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.bt_get_news);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);
        mListView = (ListView) findViewById(R.id.lv);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != mNews && mNews.size() > position)
                    Toast.makeText(MainActivity.this, mNews.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                String url = "http://api.dagoogle.cn/";
                requestNews(url);
            }
        });
    }

    private void requestNews(String url) {
        RetrofitManager.getRetrofit(MainActivity.this.getApplicationContext(), url).create(NetApi.class).requestNews(1, 20, 1)
                .subscribeOn(Schedulers.io())
                .map(new Function<NetResult, List<NewsBean>>() {
                    @Override
                    public List<NewsBean> apply(NetResult news) throws Exception {
                        if (null != news)
                            return news.getData();
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NewsBean>>() {
                    @Override
                    public void accept(final List<NewsBean> newsBeen) throws Exception {
//                        final Realm instance = RealmManager.getInstance(MainActivity.this.getApplicationContext());
//                        instance.beginTransaction();
//                        instance.copyToRealmOrUpdate(newsBeen);
//                        instance.commitTransaction();
//                        RealmResults<NewsBean> all = instance.where(NewsBean.class).findAll();
//                        android.util.Log.d("ndh--", "size==" + all.size());
//                        instance.close();
                        mNews = newsBeen;
                        mListView.setAdapter(new NewsAdapter(mNews, MainActivity.this));
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                })
        ;
    }

    public Observable<File[]> getFileObservale() {
        return Observable.create(new ObservableOnSubscribe<File[]>() {
            @Override
            public void subscribe(ObservableEmitter<File[]> e) throws Exception {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(absolutePath);
                if (file.exists() && file.isDirectory()) {
                    e.onNext(file.listFiles());
                    e.onComplete();
                } else {
                    e.onError(new FileNotFoundException());
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Flowable<File[]> getFiles() {
        return Flowable.create(new FlowableOnSubscribe<File[]>() {
            @Override
            public void subscribe(FlowableEmitter<File[]> e) throws Exception {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(absolutePath);
                if (file.exists() && file.isDirectory()) {
                    e.onNext(file.listFiles());
                    e.onComplete();
                } else {
                    e.onError(new FileNotFoundException());
                }
            }
        }, BackpressureStrategy.ERROR);
    }

}
