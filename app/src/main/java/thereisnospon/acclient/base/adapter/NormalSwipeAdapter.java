package thereisnospon.acclient.base.adapter;

import java.util.List;

import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;

/**
 * Created by yzr on 16/8/20.
 */
public abstract class NormalSwipeAdapter<T> extends BaseSwipeAdapter<T> {


    List<T>mDataList;

    public NormalSwipeAdapter(List<T> list) {
        this.mDataList = list;
    }

    public static final int ITEM_TYPE=1;

    @Override
    public int getNormalItemViewType(int position) {
        return ITEM_TYPE;
    }

    public T getItem(int position){
        return mDataList.get(position);
    }

    @Override
    public int getNormalItemCount() {
        return mDataList==null?0:mDataList.size();
    }

}
