package thereisnospon.acclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.NormalSwipeAdapter;
import thereisnospon.acclient.data.SearchPeopleItem;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.user_detail.UserDetailActivity;

/**
 * Created by yzr on 16/8/20.
 */
public class SearchPeopleAdapter extends NormalSwipeAdapter<SearchPeopleItem> {

    public SearchPeopleAdapter(List<SearchPeopleItem> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_people,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void bindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final SearchPeopleItem people=getItem(position);
        ItemViewHolder vh=(ItemViewHolder)viewHolder;
        vh.nickName.setText(people.getName());
        vh.acNum.setText(people.getAccepted()+"");
        vh.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetail(v.getContext(), people.getId());
            }
        });
    }

    private void  goToDetail(Context context, String id){
        Intent intent=new Intent(context, UserDetailActivity.class);
        intent.putExtra(Arg.LOAD_USER_DETAIL,id);
        context.startActivity(intent);
    }

    private  class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView nickName;
        TextView acNum;
        public ItemViewHolder(View itemView) {
            super(itemView);
            nickName=(TextView)itemView.findViewById(R.id.search_people_nickname);
            acNum=(TextView)itemView.findViewById(R.id.search_people_ac);
        }
    }
}
