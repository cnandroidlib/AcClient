package thereisnospon.acclient.base.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by yzr on 16/6/5.
 */
public abstract class BaseSwipeAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final  int TYPE_ITEM=0;
    public static final  int TYPE_FOOTER=1;

    private List<T> list;

    private boolean isFirst=true;

    public BaseSwipeAdapter(List<T>list){
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER){
            return onCreateFooterViewHolder(parent);
        }else{
            return  onCreateItemViewHolder(parent,viewType);
        }
    }


    public abstract  @LayoutRes
    int  getFooterLayoutId();
    public abstract  @IdRes
    int getProgressBarId();

    private FooterViewHolder onCreateFooterViewHolder(ViewGroup parent){
        View view= LayoutInflater
                .from(parent.getContext())
                .inflate(getFooterLayoutId(),parent,false);
        return new FooterViewHolder(view,getProgressBarId());
    }

    public interface  LoadListener{
        boolean hasMore();
    }


    LoadListener listener;

    public void setListener(LoadListener listener) {
        this.listener = listener;
    }

    public T getItem(int position){
        return list.get(position);
    }

    public abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup,int viewType);
    public abstract void  onBindItemViewHolder(RecyclerView.ViewHolder holder,int position);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  FooterViewHolder){
            onBindFooterViewHolder((FooterViewHolder)holder);
        } else{
            onBindItemViewHolder(holder, position);
        }
        if(isFirst){
            isFirst=false;
        }
    }

    private void onBindFooterViewHolder(FooterViewHolder viewHolder){
        if(isLoading()){
            onFooterLoading(viewHolder);
        }else{
            onFooterGone(viewHolder);
        }
    }


    private boolean isLoading(){
        return !isFirst&&listener!=null&&listener.hasMore();
    }

    private void onFooterLoading(FooterViewHolder viewHolder){
        viewHolder.progressBar.setVisibility(View.VISIBLE);
    }

    private void onFooterGone(FooterViewHolder viewHolder){
        viewHolder.progressBar.setVisibility(View.GONE);
    }

    @Override
    public int getItemViewType(int position) {
        if(position+1==getItemCount()){
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size()+1;
    }


    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public FooterViewHolder(View itemView,@IdRes int progressId) {
            super(itemView);
            progressBar=(ProgressBar)itemView.findViewById(progressId);
        }
    }

}
