package thereisnospon.acclient.modules.problem_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.fragment.WebViewFragment;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.EventCode;
import thereisnospon.acclient.modules.submmit.SubmmitAnwserActivity;

/**
 * Created by yzr on 16/6/6.
 */
public class ShowProblemFragment extends WebViewFragment
        implements  ShowProblemContact.View {
    public static final String TAG="ssProblsemDetailFragment";

    int id;

    ShowProblemContact.Presenter presenter;

    public static ShowProblemFragment newInstance(int id){
        ShowProblemFragment fragment=new ShowProblemFragment();
        fragment.id=id;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_problem_detail,container,false);
        fab=(FloatingActionButton)view.findViewById(R.id.showproblem_fab);
        initWebView(view,R.id.webView);
        presenter=new ShowProblePresenter(this);
        presenter.loadProblemDetail(id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submmitAnwser();
            }
        });
        return view;
    }

    void submmitAnwser(){
        Intent intent=new Intent(getActivity(), SubmmitAnwserActivity.class);
        intent.putExtra(Arg.SBUMMIT_PROBLEM_ID,id+"");
        startActivity(intent);
    }


    @Override
    public void onSuccess(String html) {
        loadLocalHtmlL(html,"gb2312");
    }

    @Override
    public void onFailure(String msg) {
        Log.d("TTAG",msg);
    }


}
