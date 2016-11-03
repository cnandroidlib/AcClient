package thereisnospon.acclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.NormalSwipeAdapter;
import thereisnospon.acclient.data.DiscussItem;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.discuss.DiscussHelper;
import thereisnospon.acclient.modules.show_code.CodeActivity;

/**
 * Created by yzr on 16/9/9.
 */
public class DiscussItemAdapter extends NormalSwipeAdapter<DiscussItem> {

    public DiscussItemAdapter(List<DiscussItem> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_discuss, parent, false);
        return new VH(view);
    }

    @Override
    public void bindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final DiscussItem discussItem=getItem(position);
        final VH vh=(VH)viewHolder;
        vh.discussPid.setText(discussItem.getPid());
        vh.discussTitle.setText(discussItem.getTitle());
        vh.disucssUsername.setText(discussItem.getUserName());
        vh.discussDate.setText(discussItem.getDate());
        vh.discussTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDiscuss(vh.discussDate.getContext(),discussItem.getDiscussUrl());
            }
        });
    }

    private void toDiscuss(final Context context, String url){

        DiscussHelper.toDiscuss(url, new DiscussHelper.DiscussCall() {
            @Override
            public void onSuccess(String code) {
                Intent intent=new Intent(context, CodeActivity.class);
                intent.putExtra(Arg.SHOWCODE_CODE,code);
                context.startActivity(intent);
            }
            @Override
            public void onFailure(String err) {
                Toast.makeText(context,err,Toast.LENGTH_SHORT).show();
            }
        });
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.discuss_pid) TextView discussPid;
        @BindView(R.id.discuss_title) TextView discussTitle;
        @BindView(R.id.disucss_username) TextView disucssUsername;
        @BindView(R.id.discuss_date) TextView discussDate;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
