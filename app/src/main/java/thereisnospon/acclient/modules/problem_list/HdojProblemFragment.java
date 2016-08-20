package thereisnospon.acclient.modules.problem_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.List;

import thereisnospon.acclient.R;


import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.BaseSwipeFragment;
import thereisnospon.acclient.data.HdojProblem;
import thereisnospon.acclient.ui.adapter.HdojProblemAdapter;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojProblemFragment extends BaseSwipeFragment<HdojProblem> implements  HdojContact.View{



    public static final int INDEX=-1;

    HdojContact.Presenter presenter;

    int page=INDEX;

    public static  HdojProblemFragment newInstance(int page){
        HdojProblemFragment fragment=new HdojProblemFragment();
        fragment.page=page;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_problem_list,container,false);
        initRefreshViews(view, R.id.problem_list_swipe,R.id.problem_list_recycle);
        presenter=new HdojPresenterImpl(this,page);
        presenter.refresh();
        return view;
    }


    @Override
    public boolean hasMore() {
        return page==INDEX;
    }

    @Override
    public void onSuccess(List<HdojProblem> list) {
       onRefreshData(list);
    }

    @Override
    public void onFailure(String msg) {
        Logger.d(msg);
        enableLoadMore(false);
    }

    @Override
    public void onMoreProblem(List<HdojProblem> list) {
        onMoreData(list);
    }

    @Override
    public BaseSwipeAdapter<HdojProblem> createItemAdapter(List<HdojProblem> list) {
        return new HdojProblemAdapter(list);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void loadMore() {
        presenter.loadMore();
    }

    @Override
    public void refresh() {
       presenter.refresh();
    }
}