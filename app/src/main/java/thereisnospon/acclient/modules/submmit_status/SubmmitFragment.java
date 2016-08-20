package thereisnospon.acclient.modules.submmit_status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.NormalSwipeFragment;
import thereisnospon.acclient.data.SubmmitStatus;

import thereisnospon.acclient.ui.adapter.SubmmitStatusAdapter;


public class SubmmitFragment extends NormalSwipeFragment<SubmmitStatus>

implements  SubmmitStatusContact.View{

    SubmmitStatusContact.Presenter presenter;

    SubmmitQuery query;

    public static SubmmitFragment newInstance(SubmmitQuery query){
        SubmmitFragment fragment=new SubmmitFragment();
        fragment.query=query;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=normalView(inflater,container,savedInstanceState);
        presenter=new SubmmitStatuwPresenter(this);
        return view;
    }

    @Override
    public void onMoreStatus(List<SubmmitStatus> statusList) {
       onMoreData(statusList);

    }

    @Override
    public void onRefreshStatus(List<SubmmitStatus> statusList) {
      onRefreshData(statusList);
    }


    @Override
    public void onFailure(String err) {
        enableLoadMore(false);
        onRefreshCompleted();
    }

    @Override
    public BaseSwipeAdapter<SubmmitStatus> createItemAdapter(List<SubmmitStatus> list) {
        return new SubmmitStatusAdapter(list);
    }


    @Override
    public void loadMore() {
        presenter.moreStatus(query);
    }

    @Override
    public void refresh() {
        presenter.loadStatus(query);
    }
}
