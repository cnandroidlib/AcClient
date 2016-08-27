package thereisnospon.acclient.modules.rank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.List;

import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.NormalSwipeFragment;
import thereisnospon.acclient.data.RankItem;
import thereisnospon.acclient.ui.adapter.RankItemAdapter;

/**
 * Created by yzr on 16/8/27.
 */
public class RankFragment extends NormalSwipeFragment  implements RankContact.View{


    RankContact.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=normalView(inflater,container,savedInstanceState);
        presenter=new RankPresenter(this);
        Logger.d("create viwe");
        return view;
    }

    @Override
    public BaseSwipeAdapter createItemAdapter(List list) {
        return new RankItemAdapter(list);
    }


    @Override
    public void loadMore() {
        Logger.d("laod more");
        presenter.moreRankItems();
    }

    @Override
    public void refresh() {
        Logger.d("refresh");
        presenter.loadRankItems();
    }


    @Override
    public void onRefreshRankSuccess(List<RankItem> list) {
        Logger.d("refresh success");
        onRefreshData(list);
    }

    @Override
    public void onMoreRanks(List<RankItem> list) {
        Logger.d("more ranks");
        onMoreData(list);
    }

    @Override
    public void onRankFailure(String msg) {
        Logger.e(msg);
    }

}
