package thereisnospon.acclient.ui.adapter;

/**
 * Created by yzr on 16/6/5.
 */

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
import thereisnospon.acclient.data.HdojProblem;



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
import thereisnospon.acclient.data.HdojProblem;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojProblemAdapter extends BaseSwipeAdapter<HdojProblem> {

    public HdojProblemAdapter(List<HdojProblem> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_problem,viewGroup,false);
        return new VH(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        HdojProblem problem=getItem(position);
        vh.title.setText(problem.getTitle());
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
