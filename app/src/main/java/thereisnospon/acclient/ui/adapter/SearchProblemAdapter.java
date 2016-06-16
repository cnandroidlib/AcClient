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
import thereisnospon.acclient.base.adapter.BaseSwipeAdapter;
import thereisnospon.acclient.data.ProblemItem;
import thereisnospon.acclient.data.SearchProblem;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.problem_detail.ShowProblemActivity;

/**
 * Created by yzr on 16/6/10.
 */
public class SearchProblemAdapter extends BaseSwipeAdapter<SearchProblem> {


    public SearchProblemAdapter(List<SearchProblem> list) {
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
    }
    private void  goToProblemDetail(Context context, int id){
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
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
