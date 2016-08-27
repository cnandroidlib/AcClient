package thereisnospon.acclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.NormalSwipeAdapter;
import thereisnospon.acclient.data.RankItem;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.user_detail.UserDetailActivity;

/**
 * Created by yzr on 16/8/27.
 */
public class RankItemAdapter extends NormalSwipeAdapter<RankItem> {

    public RankItemAdapter(List<RankItem> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_people,parent,false);
        return new VH(view);
    }

    @Override
    public void bindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        VH vh=(VH)viewHolder;
        final RankItem item=getItem(position);
        vh.nickName.setText(item.getName());
        vh.acNum.setText(item.getSolved()+"");
        vh.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToDetail(v.getContext(),item.getId());
            }
        });
    }


    private void  goToDetail(Context context, String id){
        Intent intent=new Intent(context, UserDetailActivity.class);
        intent.putExtra(Arg.LOAD_USER_DETAIL,id);
        context.startActivity(intent);
    }


    static class VH extends RecyclerView.ViewHolder{
        TextView nickName;
        TextView acNum;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            nickName=(TextView)itemView.findViewById(R.id.search_people_nickname);
            acNum=(TextView)itemView.findViewById(R.id.search_people_ac);
        }
    }
}
