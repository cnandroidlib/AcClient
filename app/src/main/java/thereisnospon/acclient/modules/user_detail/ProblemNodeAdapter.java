package thereisnospon.acclient.modules.user_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.data.UserInfo;

/**
 * Created by yzr on 16/8/22.
 */
public class ProblemNodeAdapter extends RecyclerView.Adapter<ProblemNodeAdapter.VH> {



    List<UserInfo.Node>nodes;

    List<UserInfo.Node>solveds;
    List<UserInfo.Node>unsolved;

    public ProblemNodeAdapter(List<UserInfo.Node> solveds, List<UserInfo.Node> unsolved) {
        this.solveds = solveds;
        this.unsolved = unsolved;
        this.nodes=new ArrayList<>();
        for(UserInfo.Node n:solveds)
            nodes.add(n);
        for(UserInfo.Node n:unsolved)
            nodes.add(n);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_pro_node,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        UserInfo.Node node=nodes.get(position);
        holder.ac.setText(node.getTag1()+"");
        holder.submmit.setText(node.getTag2()+"");
        holder.pid.setText(node.getId()+"");
    }


    @Override
    public int getItemCount() {
        return nodes!=null?nodes.size():0;
    }

    static class VH extends RecyclerView.ViewHolder{

        @BindView(R.id.user_detail_node_ac)TextView ac;
        @BindView(R.id.user_detail_node_pid)TextView pid;
        @BindView(R.id.user_detail_node_submmit)TextView submmit;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
