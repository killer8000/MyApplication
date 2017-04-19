package performance.com.example.ndh.mvpdemo.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T, H extends CommonViewHolder> extends BaseAdapter {
    private Context mContext;
    /**
     * 用来存储adapter数据的List
     */
    private List<T> mDatas = new ArrayList<T>();

    public CommonAdapter(Context context) {
        this.mContext = context;
    }

    public CommonAdapter(List<T> list, Context context) {
        mDatas = list;
        mContext = context;
    }

    public void setDatas(List<T> list) {
        mDatas = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int arg0) {
        return mDatas.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup group) {
        if (convertView == null) {
            convertView = initViewHolder().viewInflate(mContext, group, false);
        }
        H h = (H) convertView.getTag();
        h.initItemData(position, mDatas, convertView);
        return convertView;
    }

    /**
     * 返回ViewHolder子视图的抽象方法，在getView中调用，然后在子类中具体实现
     *
     * @return 返回ViewHolder
     */
    protected abstract H initViewHolder();


}
