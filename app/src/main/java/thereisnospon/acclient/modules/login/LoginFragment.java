package thereisnospon.acclient.modules.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yzr on 16/10/30.
 */

public class LoginFragment extends Fragment  implements LoginContact.View{





    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onLoginSuccess(String userName) {

    }

    @Override
    public void onLoginFailure(String error) {

    }

    @Override
    public void onRegisterSuccess(String userName) {

    }

    @Override
    public void onRegisterFailure(String error) {

    }

    @Override
    public void onCheckCode(Bitmap bitmap) {

    }

    @Override
    public void onCheckCodeErr(String error) {

    }
}
