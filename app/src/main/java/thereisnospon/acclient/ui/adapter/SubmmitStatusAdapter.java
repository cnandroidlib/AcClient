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
import thereisnospon.acclient.base.adapter.NormalSwipeAdapter;
import thereisnospon.acclient.data.SubmmitStatus;

/**
 * Created by yzr on 16/8/20.
 */
public class SubmmitStatusAdapter extends NormalSwipeAdapter<SubmmitStatus> {


    public SubmmitStatusAdapter(List<SubmmitStatus> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_submmit_status,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void bindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SubmmitStatus status=getItem(position);
        ItemViewHolder vh=(ItemViewHolder)viewHolder;

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
