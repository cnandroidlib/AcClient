package thereisnospon.acclient.modules.user_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import thereisnospon.acclient.R;
import thereisnospon.acclient.data.SubmmitStatus;
import thereisnospon.acclient.data.UserInfo;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.submmit_status.SubmmitQuery;
import thereisnospon.acclient.modules.submmit_status.SubmmitStatusActivity;

/**
 * Created by yzr on 16/6/18.
 */
public class UserDetailFragment extends Fragment implements UserDetailContact.View {

    private static final String TAG = "UserDetailFragment";

    @BindView(R.id.user_detail) TextView userDetail;
    @BindView(R.id.user_local) TextView userLocal;
    @BindView(R.id.user_time) TextView userTime;
    @BindView(R.id.user_rank) TextView userRank;
    @BindView(R.id.user_submmit) TextView userSubmmit;
    @BindView(R.id.user_solved) TextView userSolved;
    @BindView(R.id.user_submmision) TextView userSubmmision;
    @BindView(R.id.user_ac) TextView userAc;
    @BindView(R.id.usernode_recycle)RecyclerView recyclerView;

    @BindView(R.id.linear_submission)LinearLayout linearSubmission;
    @BindView(R.id.user_card_ac)CardView cardAC;
    @BindView(R.id.user_card_submission)CardView cardSubmission;

    private UserDetailContact.Presenter presenter;

    @BindView(R.id.user_info_nickname) TextView nickName;



    @OnClick(R.id.user_card_submission)void cl(){
        Intent intent=new Intent(getActivity(), SubmmitStatusActivity.class);
        intent.putExtra(Arg.SUBMMIT_QUERY_USER,id);
        intent.putExtra(Arg.SUBMMIT_QUERY_STATUS, SubmmitQuery.Status.ALL.getValue());
        startActivity(intent);
    }


    @OnClick(R.id.user_card_ac)void ac(){
        Intent intent=new Intent(getActivity(), SubmmitStatusActivity.class);
        intent.putExtra(Arg.SUBMMIT_QUERY_USER,id);
        intent.putExtra(Arg.SUBMMIT_QUERY_STATUS, SubmmitQuery.Status.AC.getValue());
        startActivity(intent);
    }

    private String id;

    public static UserDetailFragment newInstance(String id) {
        UserDetailFragment fragment = new UserDetailFragment();
        fragment.id = id;
        return fragment;
    }

    private void initView(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail_wrapper, container, false);
        ButterKnife.bind(this, view);
        presenter = new UserDetailPresenter(this);
        presenter.loadUser(id);
        return view;
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        nickName.setText(userInfo.getName());
        userDetail.setText(userInfo.getDes());
        userLocal.setText(userInfo.getSchool());
        userRank.setText(userInfo.getRank()+"");
        userSubmmision.setText(userInfo.getSubmission()+"");
        userSubmmit.setText(userInfo.getSubmitted()+"");
        userTime.setText(userInfo.getRegsiterDate()+"");
        userSolved.setText(userInfo.getSolved()+"");
        userAc.setText(userInfo.getAccepted()+"");
        ProblemNodeAdapter adapter=new ProblemNodeAdapter(id,
                userInfo.getAcceptedNodeList(),userInfo.getUnacceptedNodeList());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new ScaleInAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onError(String err) {
        Log.d(TAG, "onError: " + err);
    }
}
