package performance.com.example.ndh.mvpdemo.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonViewHolder<T> {

    private Context mContext;
    private SparseArray<View> mViewArray = new SparseArray<View>();
    /**
     * 当前项的view
     */
    private View mItemView;

    public View viewInflate(Context context, ViewGroup parent, boolean attachToRoot) {
        mContext = context;
        mItemView = LayoutInflater.from(mContext).inflate(getItemLayout(), parent, attachToRoot);
        mItemView.setTag(this);
        initItemView();
        return mItemView;
    }

    /**
     * 根据layoutI找到ItemView,这个工作只在这里声明，然后在viewInflaite里面调用，具体的实现工作交给子类去完成
     *
     * @return 发挥当前ViewHolder所需的布局或者是视图
     */
    public abstract int getItemLayout();

    /**
     * 初始化item的子View
     */
    public abstract void initItemView();

    /**
     * 初始化View根据layoutId
     *
     * @param layoutId 需要初始化的View的id
     * @return
     */
    public <T extends View> T findViewById(int layoutId) {
        View view = mViewArray.get(layoutId);
        if (view == null) {
            view = mItemView.findViewById(layoutId);
            if (view != null) {
                mViewArray.put(layoutId, view);
            }
        }
        return view == null ? null : (T) view;
    }

    protected abstract void initItemData(int position, List<T> list, View root);

}
