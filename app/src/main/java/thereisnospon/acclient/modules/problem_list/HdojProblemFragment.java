package thereisnospon.acclient.modules.problem_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.BaseSwipeFragment;
import thereisnospon.acclient.data.HdojProblem;
import thereisnospon.acclient.data.ProblemItem;
import thereisnospon.acclient.ui.adapter.HdojProblemAdapter;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojProblemFragment extends BaseSwipeFragment<HdojProblem> implements  HdojContact.View{


    HdojContact.Presenter presenter;

    int page=-1;

    public static  HdojProblemFragment newInstance(int page){
         HdojProblemFragment fragment=new HdojProblemFragment();
        fragment.page=page;
        return fragment;
    }

    @Override
    public void onLoadMore() {
        if(page==-1)
            presenter.loadMore();
        else presenter.loadPage(page);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_problem_list,container,false);
        init(view, R.id.problem_list_swipe,R.id.problem_list_recycle);
        presenter=new HdojPresenterImpl(this);
        presenter.loadPage(page);
        Logger.d("create view");
        return view;
    }

    @Override
    public BaseSwipeAdapter<HdojProblem> createAdapter(List<HdojProblem> list) {
        return new HdojProblemAdapter(list);
    }

    @Override
    public boolean hasMore() {
        return page==-1;
    }

    @Override
    public void onRefresh() {
        if(page==-1)
         presenter.refresh();
        else presenter.loadPage(page);
    }

    @Override
    public void onSuccess(List<HdojProblem> list) {
        clearData();
        onMore(list);
    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onMoreProblem(List<HdojProblem> list) {
        onMore(list);
    }
}