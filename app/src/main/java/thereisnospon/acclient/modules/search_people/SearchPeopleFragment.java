package thereisnospon.acclient.modules.search_people;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    @Override
    public void refreshPeople(List<SearchPeopleItem> list) {
        clearData();
        onMore(list);
    }

    @Override
    public void loadMorePeople(List<SearchPeopleItem> list) {
        onMore(list);
    }

    @Override
    public void onFailure(String err) {
        Log.d(TAG, "onFailure: "+err);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_people,container,false);
        init(view,R.id.search_people_swipe,R.id.search_people_recycle);
        presenter=new SearchPeoplePresenter(this);
        return view;
    }

    @Override
    public void onLoadMore() {
        presenter.loadMorePeople(key);
    }

    @Override
    public BaseSwipeAdapter<SearchPeopleItem> createAdapter(List<SearchPeopleItem> list) {
        return new SearchPeopleAdapter(list);
    }

    @Override
    public boolean hasMore() {
        return true;
    }

    @Override
    public void onRefresh() {
        presenter.searchPeople(key);
    }
}
