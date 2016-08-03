package thereisnospon.acclient.ui.adapter;

/**
 * Created by yzr on 16/6/5.
 */

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
import thereisnospon.acclient.MainActivity;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.data.HdojProblem;




import thereisnospon.acclient.data.ProblemItem;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.problem_detail.ShowProblemActivity;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojProblemAdapter extends BaseSwipeAdapter<HdojProblem> {



    public HdojProblemAdapter(List<HdojProblem > list) {
        super(list);
    }


    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_problem,viewGroup,false);
        return new VH(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        VH vh=(VH)holder;
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
    private void  goToProblemDetail(Context context,int id){
        Intent intent=new Intent(context, ShowProblemActivity.class);
        intent.putExtra(Arg.LOAD_PROBLEM_DETAIL,id);
        context.startActivity(intent);
    }

    @Override
    public int getFooterLayoutId() {
        return R.layout.item_footer;
    }

    @Override
    public int getProgressBarId() {
        return R.id.progress_bar;
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
