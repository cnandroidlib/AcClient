package thereisnospon.acclient.modules.discuss;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.utils.net.HttpUtil;

/**
 * Created by yzr on 16/11/3.
 */

public class DiscussHelper {

    public interface  DiscussCall{
        void onSuccess(String code);
        void onFailure(String err);
    }

    public static void toDiscuss(final String url, final DiscussCall call){
        Observable.just(url)
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return parseCode(getHtml(url));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        call.onSuccess(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        call.onFailure(throwable.getMessage());
                    }
                });
    }


    private static String parseCode(String html){
        if(html==null)
            return "";
        Document document=Jsoup.parse(html);
        Element disshow=document.getElementById("disshow");
        if(disshow!=null)
            return disshow.text();
        Element dispostcontent=document.getElementById("dispostcontent");
        if(dispostcontent!=null)
            return dispostcontent.text();
        return "";
    }

    private static String getHtml(String url){
       try{
           Response re=HttpUtil.getInstance()
                   .get(url)
                   .execute();
           if(!re.isSuccessful()){
               return null;
           }
           return new String(re.body().bytes(),"gb2312");
       }catch (IOException e){
           e.printStackTrace();
       }
        return null;
    }

}
