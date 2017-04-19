package performance.com.example.ndh.rxandroiddemo.realm;

import java.util.List;

/**
 * Created by ndh on 17/4/7.
 */

public class NetResult {
    int status;
    String error;
    int count;
    List<NewsBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<NewsBean> getData() {
        return data;
    }

    public void setData(List<NewsBean> data) {
        this.data = data;
    }
}
