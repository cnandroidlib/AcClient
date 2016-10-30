package thereisnospon.acclient.modules.login;

import android.graphics.Bitmap;

/**
 * Created by yzr on 16/10/30.
 */

public interface LoginContact  {


    public interface View{
        void onLoginSuccess(String userName);
        void onLoginFailure(String error);
        void onRegisterSuccess(String userName);
        void onRegisterFailure(String error);
        void onCheckCode(Bitmap bitmap);
        void onCheckCodeErr(String error);
    }

    public interface Presenter{
        void login(String name,String password);
        void register(String name,String email,String password,String chechPassword,String checkcode);
        void loadCheckCode();
    }

    public interface Model{
        String login(String name,String password);
        String register(String name,String email,String password,String chechPassword,String checkcode);
        Bitmap checkCode();
    }

}
