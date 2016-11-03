package thereisnospon.acclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.show_code.CodeActivity;
import thereisnospon.acclient.utils.SpUtil;

/**
 * Created by yzr on 16/8/20.
 */
public class SubmmitStatusAdapter extends NormalSwipeAdapter<SubmmitStatus> {




    public SubmmitStatusAdapter(List<SubmmitStatus> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_submmit_status, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void bindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final SubmmitStatus status = getItem(position);
        final ItemViewHolder vh = (ItemViewHolder) viewHolder;
        vh.sbStatus.setText(status.getStatus());
        vh.sbName.setText(status.getUserName());
        vh.sbUsedt.setText(status.getUsedTime());
        vh.sbMemory.setText(status.getUsedMemory());
        vh.sbLength.setText(status.getCodeLen());
        vh.sbLanguage.setText(status.getLanguage());
        vh.sbDate.setText(status.getDate());
        vh.sbPid.setText(status.getProblemId());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSubmmitCode(v,status.getSubmmitId(),status.getUserId());
            }
        });
    }
    private void toSubmmitCode(View view,String id, String userId){

        SpUtil spUtil=SpUtil.getInstance();
        Context context=view.getContext();
        if(userId!=null&&userId.equals(spUtil.getString(SpUtil.NAME))){
            Intent intent=new Intent(context, CodeActivity.class);
            intent.putExtra(Arg.SHOWCODE_ID,id);
            context.startActivity(intent);
        }else{
            Snackbar.make(view,"只能查看自己的代码",Snackbar.LENGTH_SHORT).show();
        }
    }




    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sb_status) TextView sbStatus;
        @BindView(R.id.sb_name) TextView sbName;
        @BindView(R.id.sb_usedt) TextView sbUsedt;
        @BindView(R.id.sb_memory) TextView sbMemory;
        @BindView(R.id.sb_length) TextView sbLength;
        @BindView(R.id.sb_language) TextView sbLanguage;
        @BindView(R.id.sb_date) TextView sbDate;
        @BindView(R.id.sb_pid)TextView sbPid;
        View itemView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView=itemView;
        }
    }

}
