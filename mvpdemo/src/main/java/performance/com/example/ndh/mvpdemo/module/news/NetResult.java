package performance.com.example.ndh.mvpdemo.module.news;

import java.util.List;

import io.realm.annotations.PrimaryKey;

/**
 * Created by ndh on 17/4/14.
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

   public class NewsBean {
        @PrimaryKey
        String news_id;
        String title;
        String top_image;
        String text_image0;
        String text_image1;
        String source;
        String content;
        String digest;
        String reply_count;
        String edit_time;
        int state = 0;// 0 UNREAD 1 READ 2 DEL

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTop_image() {
            return top_image;
        }

        public void setTop_image(String top_image) {
            this.top_image = top_image;
        }

        public String getText_image0() {
            return text_image0;
        }

        public void setText_image0(String text_image0) {
            this.text_image0 = text_image0;
        }

        public String getText_image1() {
            return text_image1;
        }

        public void setText_image1(String text_image1) {
            this.text_image1 = text_image1;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public String getEdit_time() {
            return edit_time;
        }

        public void setEdit_time(String edit_time) {
            this.edit_time = edit_time;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
