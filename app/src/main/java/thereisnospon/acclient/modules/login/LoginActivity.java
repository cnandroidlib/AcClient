package thereisnospon.acclient.modules.login;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;
import rx.functions.Action1;
import thereisnospon.acclient.R;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.EventCode;
import thereisnospon.acclient.event.Msg;
import thereisnospon.acclient.utils.SpUtil;
import thereisnospon.acclient.utils.net.callback.StringCallback;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_username)
    TextInputEditText loginUsername;
    @BindView(R.id.loging_password)
    TextInputEditText logingPassword;
    @BindView(R.id.login_loginbtn)
    Button loginLoginbtn;
    @BindView(R.id.login_check)
    CheckBox loginCheck;


    @OnClick(R.id.login_loginbtn)
    public void onClick() {
        final String userName = loginUsername.getText().toString();
        final String password = logingPassword.getText().toString();
        LoginUtil.login(userName, password, new LoginUtil.Call() {
            @Override
            public void success(String nickName) {
                Msg.t("登陆成功");
                if(loginCheck.isChecked()){
                    SpUtil.getInstance().putString(SpUtil.NAME,userName);
                    SpUtil.getInstance().putString(SpUtil.PASS,password);
                }else{
                    SpUtil.getInstance().clear();

                }
            }
            @Override
            public void failure(String msg) {
                Msg.t("登陆失败"+msg);
            }
        });
    }

    void mainMsg(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Msg.t(msg);
            }
        });

    }

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        handler=new Handler();

    }


}

