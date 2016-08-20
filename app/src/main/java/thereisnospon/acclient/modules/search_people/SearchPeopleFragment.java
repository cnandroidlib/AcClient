package thereisnospon.acclient.modules.search_people;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import thereisnospon.acclient.R;

import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.fragment.BaseSwipeFragment;
import thereisnospon.acclient.data.SearchPeopleItem;
import thereisnospon.acclient.ui.adapter.SearchPeopleAdapter;

/**
 * Created by yzr on 16/6/16.
 */
public class SearchPeopleFragment extends BaseSwipeFragment<SearchPeopleItem>
        implements  SearchPeopleContact.View{

    private static final String TAG="SearchPeopleContact";
    private SearchPeopleContact.Presenter presenter;

    private String key;

    public static SearchPeopleFragment newInstance(String key){
       SearchPeopleFragment fragment=new SearchPeopleFragment();
        fragment.key=key;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_people,container,false);
        initRefreshViews(view,R.id.search_people_swipe,R.id.search_people_recycle);
        presenter=new SearchPeoplePresenter(this);
        return view;
    }

    @Override
    public BaseSwipeAdapter<SearchPeopleItem> createItemAdapter(List<SearchPeopleItem> list) {
        return new SearchPeopleAdapter(list);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void loadMore() {
        presenter.loadMorePeople(key);
    }

    @Override
    public void refresh() {
        presenter.searchPeople(key);
    }

    @Override
    public void refreshPeople(List<SearchPeopleItem> list) {
        onRefreshData(list);
    }

    @Override
    public void loadMorePeople(List<SearchPeopleItem> list) {
        onMoreData(list);
    }

    @Override
    public void onFailure(String err) {
        Log.d(TAG, "onFailure: "+err);
        enableLoadMore(false);
    }
}
