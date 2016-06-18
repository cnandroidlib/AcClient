package thereisnospon.acclient.modules.user_detail;

import java.io.IOException;

import okhttp3.Response;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.UserInfo;
import thereisnospon.acclient.utils.net.HttpUtil;
import thereisnospon.acclient.utils.net.request.IRequest;

/**
 * Created by yzr on 16/6/18.
 */
public class UserDetailModel implements UserDetailContact.Model {

    @Override
    public UserInfo loadUser(String id) {
        String html=getHtml(id);
        if(html!=null){
            UserInfo info=UserInfo.Builder.parse(html);
            return info;
        }
        return null;
    }
    private String getHtml(String id){
        try{
            IRequest request= HttpUtil.getInstance()
                    .get(HdojApi.USER_INFO)
                    .addParameter("user",id);
            Response response=request.execute();
            String html=new String(response.body().bytes(),"gb2312");
            return html;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
