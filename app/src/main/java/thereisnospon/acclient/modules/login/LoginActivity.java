package thereisnospon.acclient.modules.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import thereisnospon.acclient.R;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.problem_list.HdojActivity;
import thereisnospon.acclient.utils.SpUtil;
import thereisnospon.acclient.widget.TransitiionListenerAdapter;

public class LoginActivity extends AppCompatActivity implements LoginContact.View{



    Scene index;
    Scene login;

    ViewGroup sceneRoot;

    void initScene(){
        sceneRoot=(ViewGroup) findViewById(R.id.scene_root);
        index=Scene.getSceneForLayout(sceneRoot,R.layout.activity_hello_scene_index,this);
        login=Scene.getSceneForLayout(sceneRoot,R.layout.activity_hello_scene_login,this);
    }


    ImageView helloLogo;
    TextView helloTitle;
    LinearLayout loginLinear;
    Button loginButton;
    EditText loginId;
    EditText loginPass;
    CheckBox remember;
    Button registerButton;


    private boolean rememberPas=false;


    void initView(){
        helloLogo=(ImageView)findViewById(R.id.hello_logo);
        helloTitle=(TextView)findViewById(R.id.hello_title);
        loginLinear=(LinearLayout)findViewById(R.id.login_linear);
        loginButton=(Button)findViewById(R.id.login_button);
        loginId=(EditText)findViewById(R.id.login_id);
        loginPass=(EditText)findViewById(R.id.login_pass);
        remember=(CheckBox)findViewById(R.id.login_remember);
        registerButton=(Button)findViewById(R.id.register_button);
        if(registerButton!=null){
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            });
        }
        if(remember!=null){
            remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    rememberPas=isChecked;
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
        if(loginButton!=null){
           loginButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   LoginActivity.this.id =loginId.getText().toString();
                   LoginActivity.this.pass=loginPass.getText().toString();
                   Toast.makeText(LoginActivity.this,id+pass+"",Toast.LENGTH_SHORT).show();
                   presenter.login(id,pass);
               }
           });
        }
    }


    private String id;
    private String pass;
    private String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        presenter=new LoginPresenter(this);
        initScene();
        initView();
        Intent intent=getIntent();
        if(intent.hasExtra(Arg.RE_LOGIN)){
            reLogin();
        }else {
            tryLogin();
        }
    }

    private boolean isShowLoingUI=false;

    private void tryLogin(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checkRemebered()){
                    presenter.login(id,pass);
                }else {
                    shoLoginUI();
                }
            }
        },1000);
    }

    private void reLogin(){
        shoLoginUI();
    }




    private boolean checkRemebered() {
        SpUtil sp = SpUtil.getInstance();
        this.id = sp.getString(SpUtil.NAME);
        this.pass = sp.getString(SpUtil.PASS);
        this.nickname = sp.getString(SpUtil.NICKNAME);
        rememberPas= this.id != null && this.pass != null;
        return rememberPas;
    }


    private void shoLoginUI() {



        isShowLoingUI=true;

        TransitionSet transitionSet=new TransitionSet();
        transitionSet.addTransition(new AutoTransition());
        transitionSet.setDuration(1000);
        transitionSet.addListener(new TransitiionListenerAdapter(){
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                initView();
            }
        });
        TransitionManager.go(login, transitionSet);

//        loginLinear.setVisibility(View.VISIBLE);
//        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1f);
//        valueAnimator.setInterpolator(new FastOutSlowInInterpolator());
//        valueAnimator.setDuration(1000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                loginLinear.setAlpha((Float) animation.getAnimatedValue());
//            }
//        });
//        valueAnimator.start();
    }


    LoginContact.Presenter presenter;




    @Override
    public void onLoginSuccess(String userName) {
        SpUtil sp=SpUtil.getInstance();
        nickname=userName;
        //Toast.makeText(this,userName,Toast.LENGTH_SHORT).show();
        if(rememberPas){
            sp.putString(SpUtil.NAME,id);
            sp.putString(SpUtil.PASS,pass);
            sp.putString(SpUtil.NICKNAME,nickname);
        }else {
            sp.clear();
            sp.putString(SpUtil.NAME,id);
            sp.putString(SpUtil.NICKNAME,nickname);
        }
        Intent intent = new Intent(this, HdojActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure(String error) {
        if(!isShowLoingUI){
            shoLoginUI();
        }else{
            Snackbar.make(helloLogo,"登陆失败",Snackbar.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRegisterSuccess(String userName) {}

    @Override
    public void onRegisterFailure(String error) {}

    @Override
    public void onCheckCode(Bitmap bitmap) {

    }

    @Override
    public void onCheckCodeErr(String error) {

    }
}
