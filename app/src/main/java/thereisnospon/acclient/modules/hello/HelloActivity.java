package thereisnospon.acclient.modules.hello;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thereisnospon.acclient.R;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.login.LoginUtil;
import thereisnospon.acclient.modules.problem_list.HdojActivity;
import thereisnospon.acclient.modules.rank.RankActivity;
import thereisnospon.acclient.utils.SpUtil;
import thereisnospon.acclient.utils.StringCall;

public class HelloActivity extends AppCompatActivity {


    @BindView(R.id.hello_logo) ImageView helloLogo;
    @BindView(R.id.hello_title) TextView helloTitle;
    @BindView(R.id.login_linear) LinearLayout loginLinear;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.login_id) EditText loginId;
    @BindView(R.id.login_pass) EditText loginPass;
    @BindView(R.id.login_remember)CheckBox remember;

    private String id;
    private String pass;
    private String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        ButterKnife.bind(this);
        initView();
        Intent intent=getIntent();
        if(intent.hasExtra(Arg.RE_LOGIN)){
            reLogin();
        }else {
            login();
        }
    }

    private void login(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkLogin()) {
                    remember.setChecked(true);
                    onLoginSuccess();
                } else {
                    showLogin();
                }
            }
        },1000);
    }

    private void reLogin(){
        showLogin();
    }


    void initView(){
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Snackbar.make(helloLogo,"记住密码,以后不用登陆",Snackbar.LENGTH_SHORT)
                            .setAction("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    remember.setChecked(false);
                                }
                            })
                            .show();
                }
            }
        });
    }

    private boolean checkLogin() {
        SpUtil sp = SpUtil.getInstance();
        this.id = sp.getString(SpUtil.NAME);
        this.pass = sp.getString(SpUtil.PASS);
        this.nickname = sp.getString(SpUtil.NICKNAME);
        return this.id != null && this.pass != null;
    }

    private void showLogin() {
        loginLinear.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1f);
        valueAnimator.setInterpolator(new FastOutSlowInInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                loginLinear.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClick() {
        this.id =loginId.getText().toString();
        this.pass=loginPass.getText().toString();
        LoginUtil.login(id,pass,new LoginUtil.Call(){
            @Override
            public void success(String nickName) {
                HelloActivity.this.nickname=nickName;
                onLoginSuccess();
            }
            @Override
            public void failure(String msg) {
                onLoginFailure(msg);
            }
        });
    }

    private void onLoginSuccess(){
        SpUtil sp=SpUtil.getInstance();
        if(remember.isChecked()){
            sp.putString(SpUtil.NAME,id);
            sp.putString(SpUtil.PASS,pass);
            sp.putString(SpUtil.NICKNAME,nickname);
        }else {
            sp.clear();
        }
        Intent intent = new Intent(this, HdojActivity.class);
        startActivity(intent);
        finish();
    }

    private void onLoginFailure(String msg){
        Logger.d(msg);
        Snackbar.make(helloLogo,"登陆失败",Snackbar.LENGTH_SHORT).show();
    }


}
