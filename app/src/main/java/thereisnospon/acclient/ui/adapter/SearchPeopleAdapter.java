package thereisnospon.acclient.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.base.adapter.NormalSwipeAdapter;
import thereisnospon.acclient.data.SearchPeopleItem;

/**
 * Created by yzr on 16/6/16.
 */
public class SearchPeopleAdapter extends NormalSwipeAdapter<SearchPeopleItem> {

    public SearchPeopleAdapter(List<SearchPeopleItem> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_search_people,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchPeopleItem people=getItem(position);
        ItemViewHolder vh=(ItemViewHolder)holder;
        vh.nickName.setText(people.getName());
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView nickName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            nickName=(TextView)itemView.findViewById(R.id.search_people_nickname);
        }
    }
}
