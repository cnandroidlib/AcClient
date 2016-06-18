package thereisnospon.acclient.base.adapter;

import java.util.List;

import thereisnospon.acclient.R;

/**
 * Created by yzr on 16/6/16.
 */
public abstract class NormalSwipeAdapter<T> extends BaseSwipeAdapter<T> {

    public NormalSwipeAdapter(List<T> list) {
        super(list);
    }
    @Override
    public int getFooterLayoutId() {
        return R.layout.item_footer;
    }

    @Override
    public int getProgressBarId() {
        return R.id.progress_bar;
    }

}
