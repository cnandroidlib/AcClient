package thereisnospon.acclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import thereisnospon.acclient.data.ProblemItem;
import thereisnospon.acclient.data.SearchProblem;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.problem_detail.ShowProblemActivity;

/**
 * Created by yzr on 16/8/20.
 */
public class SearchProblemAdapter extends NormalSwipeAdapter<SearchProblem> {


    public SearchProblemAdapter(List<SearchProblem> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_problem,parent,false);
        return new VH(view);
    }

    @Override
    public void bindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        VH vh=(VH)viewHolder;
        final ProblemItem problem=getItem(position);
        vh.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProblemDetail(v.getContext(),problem.getId());
            }
        });
        vh.title.setText(problem.getTitle());
        vh.id.setText(problem.getId()+"");
        vh.ac.setText(problem.getAccepted()+"");
        vh.submmit.setText(problem.getSubmmision()+"");
    }


    private void  goToProblemDetail(Context context, int id){
        Intent intent=new Intent(context, ShowProblemActivity.class);
        intent.putExtra(Arg.LOAD_PROBLEM_DETAIL,id);
        context.startActivity(intent);
    }

    public class VH extends RecyclerView.ViewHolder{

        @BindView(R.id.problem_title)TextView title;
        @BindView(R.id.problem_ac)TextView ac;
        @BindView(R.id.problem_submmit)TextView submmit;
        @BindView(R.id.problem_id)TextView id;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
