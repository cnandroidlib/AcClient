package thereisnospon.acclient.modules.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.utils.net.HttpUtil;
import thereisnospon.acclient.utils.net.request.IRequest;
import thereisnospon.acclient.utils.net.request.PostRequest;

/**
 * Created by yzr on 16/10/30.
 */

public class LoginModel implements LoginContact.Model {


    /**
     * login
     * @param name
     * @param password
     * @return
     */

    @Override
    public String login(String name, String password) {
        String na= parseName(name,loginHtml(name,password));
        return na;
    }

    private boolean checkId(String id,Element a){
        String href=a.attr("href");
        if(href==null)return false;
        String ids[]=href.split("user=");
        if(ids!=null&&ids.length==2&&id.equals(ids[1])){
            return true;
        }
        return false;
    }


    private String parseName(String id,String html){
        Document document= Jsoup.parse(html);
        Elements as=document.getElementsByTag("a");
        int len=as!=null?as.size():0;
        for(int i=0;i<len;i++){
            Element a=as.get(i);
            Elements c=a.getElementsByAttributeValue("alt","Author");
            if(c!=null&&c.size()>0&&checkId(id,a)){
                return a.text();
            }
        }
        return null;
    }

    private String loginHtml(String userName,String password){
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


    /**
     *  register
     * @param name
     * @param email
     * @param password
     * @param checkPassword
     * @return
     */

    @Override
    public String register(String name, String email, String password, String checkPassword,String check) {
        return reg(name,email,password,checkPassword,check);
    }


    private String reg(String name,String email,String password,String checkPassword,String check){
        return inner_register(name,email,password,checkPassword,check)?name:null;
    }


    private boolean inner_register(String name,String email,String password,String checkPassword,String check){
        try{
            IRequest request= HttpUtil.getInstance()
                    .post(HdojApi.REGISTER_URL)
                    .addParameter("username",name)
                    .addParameter("email",email)
                    .addParameter("password1",password)
                    .addParameter("password2",checkPassword);
            if(check!=null)
                request.addParameter("check",check);
            Response respone=request.execute();
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


    @Override
    public Bitmap checkCode() {

       try{
           Response response=HttpUtil.getInstance()
                   .get(HdojApi.CHECK_CODE)
                   .execute();
           if(!response.isSuccessful()){
               return null;
           }
           InputStream inputStream=response.body().byteStream();
           return BitmapFactory.decodeStream(inputStream);
       }catch (IOException e){
           e.printStackTrace();
       }
        return null;
    }
}
