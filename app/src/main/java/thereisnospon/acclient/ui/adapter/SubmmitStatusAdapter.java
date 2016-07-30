package thereisnospon.acclient.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.adapter.NormalSwipeAdapter;
import thereisnospon.acclient.data.SubmmitStatus;

/**
 * Created by yzr on 16/6/18.
 */
public class SubmmitStatusAdapter extends NormalSwipeAdapter<SubmmitStatus> {


    public SubmmitStatusAdapter(List<SubmmitStatus> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_submmit_status,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        SubmmitStatus status=getItem(position);
        ItemViewHolder vh=(ItemViewHolder)holder;
        vh.judge.setText(status.getStatus());
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.status_judge)TextView judge;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
