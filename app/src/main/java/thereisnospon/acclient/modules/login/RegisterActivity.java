package thereisnospon.acclient.modules.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thereisnospon.acclient.R;

public class RegisterActivity extends AppCompatActivity  implements LoginContact.View{

    @BindView(R.id.hello_logo) ImageView helloLogo;
    @BindView(R.id.register_id) EditText registerId;
    @BindView(R.id.register_email) EditText registerEmail;
    @BindView(R.id.register_pass1) EditText registerPass1;
    @BindView(R.id.register_pass2) EditText registerPass2;
    @BindView(R.id.register_button) Button registerButton;
    @BindView(R.id.activity_register) RelativeLayout activityRegister;
    @BindView(R.id.check_code_img)ImageView checkImg;
    @BindView(R.id.check_code_text)EditText checkText;
    @BindView(R.id.check)LinearLayout linearLayout;




    @OnClick(R.id.check_code_img)void  cimg(){



        presenter.loadCheckCode();
    }

    private LoginContact.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter=new LoginPresenter(this);
    }




    @OnClick(R.id.register_button)
    public void register() {

        String id=registerId.getText().toString();
        String email=registerEmail.getText().toString();
        String pass=registerPass1.getText().toString();
        String passch=registerPass2.getText().toString();
        String check=null;
        if(linearLayout.getVisibility()==View.VISIBLE){
            check=checkText.getText().toString();
        }
        presenter.register(id,email,pass,passch,check);
    }



    @Override
    public void onRegisterSuccess(String userName) {
        Snackbar.make(helloLogo,"注册成功:"+userName,Snackbar.LENGTH_SHORT)
                .setAction("返回登陆", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       finish();
                    }
                }).show();
    }




    @Override
    public void onRegisterFailure(String error) {
        Logger.d(error);
        linearLayout.setVisibility(View.VISIBLE);
        presenter.loadCheckCode();
        Snackbar.make(helloLogo,error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(String userName) {}
    @Override
    public void onLoginFailure(String error) {}


    @Override
    public void onCheckCode(Bitmap bitmap) {
        checkImg.setImageBitmap(bitmap);
    }

    @Override
    public void onCheckCodeErr(String error) {
        checkImg.setImageBitmap(null);
    }
}
