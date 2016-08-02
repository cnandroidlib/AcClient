package thereisnospon.acclient.modules.submmit_status;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.List;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.BaseSwipeFragment;
import thereisnospon.acclient.base.fragment.NormalSwipeFragment;
import thereisnospon.acclient.data.SubmmitStatus;
import thereisnospon.acclient.modules.search_people.SearchPeoplePresenter;
import thereisnospon.acclient.ui.adapter.SubmmitStatusAdapter;


public class SubmmitFragment extends NormalSwipeFragment<SubmmitStatus>
implements  SubmmitStatusContact.View{

    SubmmitStatusContact.Presenter presenter;

    SubmmitQuery query;
    boolean more=true;

    int debug=0;

    boolean refreshing=false;

    public static SubmmitFragment newInstance(SubmmitQuery query){
        SubmmitFragment fragment=new SubmmitFragment();
        fragment.query=query;
        return fragment;
    }


    @Override
    public void onLoadMore() {
        if(!refreshing)
             presenter.moreStatus(query);
    }

    @Override
    public BaseSwipeAdapter<SubmmitStatus> createAdapter(List<SubmmitStatus> list) {
        return new SubmmitStatusAdapter(list);
    }

    @Override
    public boolean hasMore() {
        return more;
    }

    @Override
    public void onRefresh() {
        more=false;
        if(!refreshing)
            presenter.loadStatus(query);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=normalViewAndInit(inflater,container,savedInstanceState);
        presenter=new SubmmitStatuwPresenter(this);
        return view;
    }

    @Override
    public void onMoreStatus(List<SubmmitStatus> statusList) {
        onMore(statusList);
        refreshing=false;
        Logger.d(statusList.size());

    }

    @Override
    public void onRefreshStatus(List<SubmmitStatus> statusList) {
        clearData();
        onMore(statusList);
        refreshing=false;
        Logger.d(statusList.size());
    }

    @Override
    public void onFailure(String err) {
        more=false;
        Logger.d(err);
        refreshing=false;
    }


}
