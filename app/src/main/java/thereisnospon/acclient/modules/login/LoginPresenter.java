package thereisnospon.acclient.modules.login;

import android.graphics.Bitmap;
import android.text.TextUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.utils.StringCall;

/**
 * Created by yzr on 16/10/30.
 */

public class LoginPresenter implements LoginContact.Presenter {


    LoginContact.Model model;
    LoginContact.View view;

    public LoginPresenter(LoginContact.View view) {
        this.view = view;
        this.model=new LoginModel();
    }

    @Override
    public void login(final String name, final String message) {

        Observable.just(name)
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return model.login(name,message);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new StringCall() {
                    @Override
                    public void success(String nickName) {
                        view.onLoginSuccess(nickName);
                    }

                    @Override
                    public void failure(String msg) {
                        view.onLoginFailure(msg);
                    }
                });

    }

    @Override
    public void register(final String name, final String email, final String password, final String checkPassword, final String check) {
        if(!check(name,email,password,checkPassword)){
            return;
        }
        Observable.just(name)
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return model.register(name,email,password,checkPassword,check);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new StringCall() {
                    @Override
                    public void success(String nickName) {
                        view.onRegisterSuccess(nickName);
                    }

                    @Override
                    public void failure(String msg) {
                        view.onRegisterFailure("注册失败");
                    }
                });
    }


    private static final String CHECK_EMAIL_REGEX="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    private static Pattern pattern;
    static {
        pattern=Pattern.compile(CHECK_EMAIL_REGEX);
    }


    public static boolean checkEmail(String email){
        return email!=null&&pattern.matcher(email).matches();
    }


    public  boolean check(String name,String email,String password,String checkPassword){

        if(TextUtils.isEmpty(name)){
            view.onRegisterFailure("用户名不能为空！");
            return false;
        }

        if(!checkEmail(email)){
            view.onRegisterFailure("邮箱错误！");
            return false;
        }

        if(TextUtils.isEmpty(password)){
           view.onRegisterFailure("密码不能为空！");
            return false;
        }

        if(!password.equals(checkPassword)){
            view.onRegisterFailure("两次密码不同");
            return false;
        }

        if(password.length()<6){
            view.onRegisterFailure("密码长度至少为6");
            return false;
        }

        return true;
    }


    @Override
    public void loadCheckCode() {
        Observable.just(1)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, Bitmap>() {
                    @Override
                    public Bitmap call(Integer integer) {
                        return model.checkCode();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        if (bitmap != null) {
                            view.onCheckCode(bitmap);
                        } else {
                            view.onCheckCodeErr("null");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onCheckCodeErr(throwable.getMessage());
                    }
                });
    }
}
