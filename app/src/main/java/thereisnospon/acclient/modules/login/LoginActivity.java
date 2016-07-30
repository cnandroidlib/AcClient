package thereisnospon.acclient.modules.login;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class LoginActivity extends AppCompatActivity  {

    @BindView(R.id.login_userName)EditText userNameEdit;
    @BindView(R.id.login_password)EditText passwordEdit;
    @BindView(R.id.login_loginButton)Button loginButon;
    @BindView(R.id.login_get)Button getButton;
    @BindView(R.id.login_content)TextView  msg;

    @OnClick(R.id.login_loginButton)public void login(){
        String userName=userNameEdit.getText().toString();
        String password=passwordEdit.getText().toString();
        LoginUtil.login(userName,password);
    }

    @OnClick(R.id.login_get)public void get(){
        LoginUtil.testGet();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }


    @Subscribe
    public void onNewEvent(Event<String> event){
        switch (event.getEventCode())
        {
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

    public void onLoginSuccess(Event<String> event){
        msg.setText(event.getData());
        LoginUtil.testGet();
    }

    public void onLoginFailure(Event<String> event){
        msg.setText(event.getData());
    }

    public void onGetSuccess(Event<String>event){
        msg.setText(event.getData());
    }

    public void onGetFailure(Event<String>event){
        msg.setText(event.getData());
    }

}

