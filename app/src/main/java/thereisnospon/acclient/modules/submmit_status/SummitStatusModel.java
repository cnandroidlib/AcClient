package thereisnospon.acclient.modules.submmit_status;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.SubmmitStatus;
import thereisnospon.acclient.utils.net.HttpUtil;
import thereisnospon.acclient.utils.net.request.IRequest;

/**
 * Created by yzr on 16/6/18.
 */
public class SummitStatusModel implements SubmmitStatusContact.Model {




    @Override
    public List<SubmmitStatus> loadStatus(SubmmitQuery query) {
        return null;
    }

    @Override
    public List<SubmmitStatus> moreStatus(SubmmitQuery query) {
        return null;
    }

    private IRequest buildRequest(String first, String user, String status){
        IRequest request= HttpUtil.getInstance()
                .get(HdojApi.JUDGE_STATUS);
        if(first!=null){
            request.addParameter("first",first);
        }
        if(user!=null){
            request.addParameter("user",user);
        }
        if(status!=null){
            request.addParameter("status",status);
        }
        return request;
    }

    private String getHtml(String first,String user,String status){
        IRequest request=buildRequest(first,user,status);
        try{
            Response response=request.execute();
            String html=new String(response.body().bytes(),"gb2312");
            return html;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
