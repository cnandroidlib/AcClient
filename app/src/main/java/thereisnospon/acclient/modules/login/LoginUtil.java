package thereisnospon.acclient.modules.login;

import android.text.TextUtils;

import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.AppApplication;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.EventCode;
import thereisnospon.acclient.event.EventUtil;
import thereisnospon.acclient.utils.net.HttpUtil;
import thereisnospon.acclient.utils.net.callback.StringCallback;
import thereisnospon.acclient.utils.net.cookie.PresistentCookieStroe;

/**
 * Created by yzr on 16/6/5.
 */


public class LoginUtil {


    public abstract static class LoginCall extends Subscriber<String>{

        public abstract void success(String nickName);
        public abstract void failure(String msg);

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            failure(e.getMessage());
        }

        @Override
        public void onNext(String s) {
            if(s!=null)
                success(s);
            else failure("null");
        }
    }

    public static void login(final String userName, final String password, LoginCall call){
        Observable.just(userName)
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return parseName(loginHtml(userName,password));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(call);
    }


    static String parseName(String html){
        Document document= Jsoup.parse(html);
        Elements as=document.getElementsByTag("a");
        int len=as!=null?as.size():0;
        for(int i=0;i<len;i++){
            Element a=as.get(i);
            Elements c=a.getElementsByAttributeValue("alt","Author");
            if(c!=null&&c.size()>0)
                return a.text();
        }
        return null;
    }

   static String loginHtml(String userName,String password){
        try{
            Response res=HttpUtil.getInstance().post(HdojApi.LOGIN)
                    .addParameter("username",userName)
                    .addParameter("userpass",password)
                    .addParameter("login","Sign In")
                    .execute();
            return new String(res.body().bytes(),"gb2312");
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static void login(String userName,String password){
        HttpUtil util=HttpUtil.getInstance();
        util.post(HdojApi.LOGIN)
                .addParameter("username",userName)
                .addParameter("userpass",password)
                .addParameter("login","Sign In")
                .enqueue(new StringCallback() {
                    @Override
                    public void onFail(String msg) {
                        Event<String> ev=new Event<>(msg,EventCode.LOGIN_FAILURE);
                        EventUtil.posetEventOnMainThread(ev);
                    }
                    @Override
                    public void onSuccess(String str, Headers headers) {
                        Event<String> ev=new Event<String>(str, EventCode.LOGIN_SUCCESS);
                        EventUtil.posetEventOnMainThread(ev);
                    }
                });
    }

    public static boolean cheackCookie() {
        PresistentCookieStroe store=new PresistentCookieStroe(AppApplication.context);
        HttpUrl url=HttpUrl.get(URI.create(HdojApi.LOGIN));
        List<Cookie>cookies=store.get(url);
        for(Cookie cookie:cookies){
            if(System.currentTimeMillis()<cookie.expiresAt())
                return true;
        }
        return false;
    }

    private static final String CHECK_EMAIL_REGEX="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    private static Pattern pattern;
    static {
        pattern=Pattern.compile(CHECK_EMAIL_REGEX);
    }


    public static boolean checkEmail(String email){
       return email!=null&&pattern.matcher(email).matches();
    }


   public static boolean check(String name,String email,String password,String checkPassword,
                               LoginCall call){

       if(TextUtils.isEmpty(name)){
           call.failure("name empty");
           return false;
       }

       if(!checkEmail(email)){
           call.failure("email error");
           return false;
       }

       if(TextUtils.isEmpty(password)){
           call.failure("password empty");
           return false;
       }

       if(!password.equals(checkPassword)){
           call.failure("password not equal");
           return false;
       }

        return true;
   }

    public static void register(final String name, final String email, final String password, final String checkPassword,
                                LoginCall call){

        if(!check(name,email,password,checkPassword,call)){
            return;
        }

        Observable.just(name)
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return register(name,email,password,checkPassword)?name:null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(call);

    }


    private static boolean register(String name,String email,String password,String checkPassword){
        try{
            Response respone= HttpUtil.getInstance()
                    .post(HdojApi.REGISTER_URL)
                    .addParameter("username",name)
                    .addParameter("email",email)
                    .addParameter("password1",password)
                    .addParameter("password2",checkPassword)
                    .execute();
            if(!respone.isSuccessful()){
                return false;
            }else{
                String html=new String(respone.body().bytes(),"gb2312");
                return checkRegister(html);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }


    private static boolean checkRegister(String html){
        if(html==null){
            return false;
        }
        Document document= Jsoup.parse(html);
        Elements inputs=document.getElementsByTag("input");
        for(int i=0;inputs!=null&&i<inputs.size();i++){
            String name=inputs.get(i).attr("name");
            if("idnum".equals(name)){
                return true;
            }
        }
        return false;
    }


}
