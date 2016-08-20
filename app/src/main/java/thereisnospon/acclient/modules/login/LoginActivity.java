package thereisnospon.acclient.modules.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thereisnospon.acclient.R;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.EventCode;

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

    @OnClick(R.id.login_loginbtn)
    public void onClick() {
        String userName=loginUsername.getText().toString();
        String password=logingPassword.getText().toString();
        LoginUtil.login(userName,password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }


    @Subscribe
    public void onNewEvent(Event<String> event) {
        switch (event.getEventCode()) {
            case EventCode.LOGIN_SUCCESS:
                onLoginSuccess(event);
                break;
            case EventCode.LOGIN_FAILURE:
                onLoginFailure(event);
                break;
            case EventCode.TEST_GET_SUCCESS:
                onGetSuccess(event);
                break;
            case EventCode.TEST_GET_FAILURE:
                onGetFailure(event);
                break;
            default:
                break;
        }
    }

    public void onLoginSuccess(Event<String> event) {
        LoginUtil.testGet();
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
    }

    public void onLoginFailure(Event<String> event) {

    }

    public void onGetSuccess(Event<String> event) {

    }

    public void onGetFailure(Event<String> event) {

    }


}

