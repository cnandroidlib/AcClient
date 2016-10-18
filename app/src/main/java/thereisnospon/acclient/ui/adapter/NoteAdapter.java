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
import thereisnospon.acclient.data.NoteItem;

/**
 * Created by yzr on 16/9/9.
 */
public class NoteAdapter extends NormalSwipeAdapter<NoteItem> {


    public NoteAdapter(List<NoteItem> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note,parent,false);
        return new VH(view);
    }

    @Override
    public void bindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        NoteItem noteItem=getItem(position);
        VH vh=(VH)viewHolder;
        vh.title.setText(noteItem.getTitle());
        vh.pid.setText(noteItem.getPid());
        vh.date.setText(noteItem.getDate());
    }


    class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.note_title)TextView title;
        @BindView(R.id.note_date)TextView date;
        @BindView(R.id.note_pid)TextView pid;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
