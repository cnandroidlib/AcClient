package thereisnospon.acclient.utils.net.cookie;

import android.content.Context;
import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import thereisnospon.acclient.utils.net.HttpUtil;

/**
 * Created by yzr on 16/6/5.
 */
public class CookiesManager implements CookieJar {

    PresistentCookieStroe store;

    public CookiesManager(Context context){
        store=new PresistentCookieStroe(context.getApplicationContext());
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        if(cookies!=null&&cookies.size()>0){
            for(Cookie item:cookies){
                store.add(url,item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = store.get(url);
        return cookies;
    }


    public void  clearCookie(HttpUrl url){

    }



}
