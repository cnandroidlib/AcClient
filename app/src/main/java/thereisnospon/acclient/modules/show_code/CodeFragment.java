package thereisnospon.acclient.modules.show_code;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

/**
 * Created by yzr on 16/8/2.
 */
public class CodeFragment extends Fragment implements CodeContact.View{


    CodeContact.Presenter presenter;

   @BindView(R.id.codeView) CodeView codeView;

    String id;

    public static CodeFragment newInstance(String id){
        CodeFragment fragment=new CodeFragment();
        fragment.id=id;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_showcode,container,false);
        ButterKnife.bind(this,view);
        codeView.setTheme(CodeViewTheme.MONOKAI_SUBLIME);
        codeView.fillColor();
        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.code_back);
        linearLayout.setBackgroundColor(codeView.getCodeBackgroundColor());
        presenter=new CodePresenter(this);
        presenter.loadCode(id);
        Logger.d("create faragment");
        return view;
    }

    @Override
    public void onSuccess(String code) {
        codeView.showCode(code);
        Logger.d(code);
    }

    @Override
    public void onFailure(String err) {
        Logger.d(err);
    }
}
