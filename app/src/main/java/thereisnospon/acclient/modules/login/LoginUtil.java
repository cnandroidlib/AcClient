package thereisnospon.acclient.modules.login;

import android.util.Log;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.EventCode;
import thereisnospon.acclient.event.EventUtil;
import thereisnospon.acclient.utils.net.HttpUtil;
import thereisnospon.acclient.utils.net.callback.StringCallback;

/**
 * Created by yzr on 16/6/5.
 */


public class LoginUtil {

    public static void login(String userName,String password){
        HttpUtil util=HttpUtil.getInstance();
        util.post("http://acm.hdu.edu.cn/userloginex.php?action=login")
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

    public static void testGet(){
        HttpUtil.getInstance()
                .get("http://acm.hdu.edu.cn/viewcode.php?rid=15062064")
                .enqueue(new StringCallback() {
                    @Override
                    public void onFail(String msg) {
                        Event<String>ev=new Event<>(msg,EventCode.TEST_GET_FAILURE);
                        EventUtil.posetEventOnMainThread(ev);
                    }
                    @Override
                    public void onSuccess(String str, Headers headers) {
                        Event<String>ev=new Event<String>(str,EventCode.TEST_GET_SUCCESS);
                        EventUtil.posetEventOnMainThread(ev);
                    }
                });
    }

}
