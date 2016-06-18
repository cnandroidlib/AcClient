package thereisnospon.acclient.modules.user_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.data.UserInfo;

/**
 * Created by yzr on 16/6/18.
 */
public class UserDetailFragment extends Fragment implements UserDetailContact.View {

    private static final String TAG="UserDetailFragment";

    private UserDetailContact.Presenter presenter;


    @BindView(R.id.user_info_nickname)TextView nickName;


    private String id;
    public static UserDetailFragment newInstance(String id){
        UserDetailFragment fragment=new UserDetailFragment();
        fragment.id=id;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_detail,container,false);
        ButterKnife.bind(this,view);
        presenter=new UserDetailPresenter(this);
        presenter.loadUser(id);
        return view;
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        nickName.setText(userInfo.getName());
    }

    @Override
    public void onError(String err) {
        Log.d(TAG, "onError: "+err);
    }
}
