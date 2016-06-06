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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.BaseSwipeFragment;
import thereisnospon.acclient.data.HdojProblem;
import thereisnospon.acclient.ui.adapter.HdojProblemAdapter;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojProblemFragment extends BaseSwipeFragment<HdojProblem> implements  HdojContact.View{




    HdojContact.Presenter presenter;

    @Override
    public void onLoadMore() {
        presenter.loadMore();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_problem_list,container,false);
        init(view, R.id.problem_list_swipe,R.id.problem_list_recycle);
        presenter=new HdojPresenterImpl(this);
        return view;
    }

    @Override
    public BaseSwipeAdapter<HdojProblem> createAdapter(List<HdojProblem> list) {
        return new HdojProblemAdapter(list);
    }

    @Override
    public boolean hasMore() {
        return true;
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void onSuccess(List<HdojProblem> list) {
        onMore(list);
    }

    @Override
    public void onFailure(String msg) {

    }
}