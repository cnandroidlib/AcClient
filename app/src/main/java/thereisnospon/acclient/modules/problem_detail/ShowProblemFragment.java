package thereisnospon.acclient.modules.problem_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.fragment.WebViewFragment;

/**
 * Created by yzr on 16/6/6.
 */
public class ShowProblemFragment extends WebViewFragment
        implements  ShowProblemContact.View {
    public static final String TAG="ssProblsemDetailFragment";

    ShowProblemContact.Presenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_problem_detail,container,false);
        initWebView(view,R.id.webView);
        presenter=new ShowProblePresenter(this);
        presenter.loadProblemDetail(1000);
        return view;
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
