package thereisnospon.acclient.modules.problem_list.search_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.BaseFragment;
import thereisnospon.acclient.base.fragment.BaseSwipeFragment;
import thereisnospon.acclient.data.HdojProblem;
import thereisnospon.acclient.data.SearchProblem;
import thereisnospon.acclient.modules.problem_list.HdojPresenterImpl;
import thereisnospon.acclient.ui.adapter.HdojProblemAdapter;
import thereisnospon.acclient.ui.adapter.SearchProblemAdapter;

/**
 * Created by yzr on 16/6/10.
 */
public class SearchProblemFragment extends BaseSwipeFragment<SearchProblem>
        implements SearchProblemContact.View{

    SearchProblemContact.Presenter presenter;

    private static String TAG="SSEARCH";

    String query;

    public static SearchProblemFragment newInstance(String query){
        SearchProblemFragment fragment=new SearchProblemFragment();
        fragment.query=query;
        return fragment;
    }

    @Override
    public void onLoadMore() {
        Log.d(TAG, "onLoadMore: more"+query);
        presenter.loadMoreQuery(query);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_problem_list,container,false);
        init(view, R.id.problem_list_swipe,R.id.problem_list_recycle);
        presenter=new SearchProblemPresenter(this);
        return view;
    }

    @Override
    public BaseSwipeAdapter<SearchProblem> createAdapter(List<SearchProblem> list) {
        return new SearchProblemAdapter(list);
    }

    @Override
    public boolean hasMore() {
        return true;
    }

    @Override
    public void onRefresh() {
         Log.d(TAG, "onRefresh: "+query);
         presenter.queryProblem(query);
    }


    @Override
    public void loadSccess(List<SearchProblem> list) {
        Log.d(TAG, "loadSccess: "+list.size());
        onMore(list);
    }

    @Override
    public void refreshSuccess(List<SearchProblem> list) {
        clearData();
        Log.d(TAG, "refreshSuccess: "+list.size());
        onMore(list);
    }

    @Override
    public void onFailure(String errMsg) {
        Log.d(TAG, "onFailure: "+errMsg);
    }
}
