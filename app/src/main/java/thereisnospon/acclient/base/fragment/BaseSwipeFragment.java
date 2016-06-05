package thereisnospon.acclient.base.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;

/**
 * Created by yzr on 16/6/5.
 */
public  abstract class BaseSwipeFragment<T> extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseSwipeAdapter.LoadListener{

    public SwipeRefreshLayout swipeRefreshLayout;

    public RecyclerView recyclerView;

    private BaseSwipeAdapter<T> adapter;
    private List<T> list;


    public  void  onMore(List<T>list){
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }


    public abstract void onLoadMore();

    public void  starteRefresh(){
        swipeRefreshLayout.setRefreshing(true);
    }


    public void endRefresh(){
        swipeRefreshLayout.setRefreshing(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }


    public final void  init(View parent,
                            @IdRes int swipeRefreshLayoutId,
                            @IdRes int recycleViewLayoutId){
        swipeRefreshLayout=(SwipeRefreshLayout)parent.findViewById(swipeRefreshLayoutId);
        recyclerView=(RecyclerView)parent.findViewById(recycleViewLayoutId);
        list=new ArrayList<>();
        adapter=createAdapter(list);
        initRecycleView(recyclerView);
        adapter.setListener(this);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onLoadMore();
            }
        });
    }

    LinearLayoutManager linearLayoutManager;

    private void initRecycleView(RecyclerView recyclerView){
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        EndlessRecycleOnScrollListener listener=new EndlessRecycleOnScrollListener(linearLayoutManager);
        recyclerView.addOnScrollListener(listener);
    }


    public int getItemCount(){
        return adapter.getItemCount();
    }
    public abstract BaseSwipeAdapter<T>  createAdapter(List<T>list);


    public class EndlessRecycleOnScrollListener extends  RecyclerView.OnScrollListener{
        private int previousTotal=0;
        private boolean loading=true;
        private int visibleThreshold=1;
        int firstVisibleItem;
        int visibleItemCount;
        int totalItemCount;
        private int currentPage=1;
        private LinearLayoutManager linearLayoutManager;
        public EndlessRecycleOnScrollListener(LinearLayoutManager linearLayoutManager){
            this.linearLayoutManager=linearLayoutManager;
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            visibleItemCount=recyclerView.getChildCount();
            totalItemCount=linearLayoutManager.getItemCount();
            firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
            if(loading){
                if(totalItemCount>previousTotal){
                    loading=false;
                    previousTotal=totalItemCount;
                }
            }
            if( hasMore()&&
                    !loading
                    &&totalItemCount>visibleItemCount
                    &&(totalItemCount-visibleItemCount)<=(firstVisibleItem+visibleThreshold))
            {
                currentPage++;
                onLoadMore();
                loading=true;
            }

        }
    }

}
