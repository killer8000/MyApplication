package performance.com.example.ndh.mvpdemo.net;

import io.reactivex.Flowable;
import performance.com.example.ndh.mvpdemo.module.news.NetResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ndh on 17/3/31.
 */

public interface NetApi {
    //        ?tableNum=1&pagesize=20&page=1

    @GET("news/get-news/")
    Flowable<NetResult> requestNews(@Query("tableNum") int tableNum, @Query("pagesize") int pageSize, @Query("page") int page);

}
