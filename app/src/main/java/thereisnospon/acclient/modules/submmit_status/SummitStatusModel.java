package thereisnospon.acclient.modules.submmit_status;

import com.orhanobut.logger.Logger;

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


    QueryManager manager;

    public SummitStatusModel(){
        manager=new QueryManager();
    }

    @Override
    public List<SubmmitStatus> loadStatus(SubmmitQuery query) {
        String q=manager.load(query);
        Logger.d(q);
        return  getList(q);
    }

    @Override
    public List<SubmmitStatus> moreStatus(SubmmitQuery query) {
       String q=manager.more(query);
        Logger.d(q);
        return getList(q);
    }

    private IRequest buildRequest(String  query){
        IRequest reques=HttpUtil.getInstance()
                .get(query);
      return reques;
    }

    List<SubmmitStatus>getList(String query){
        String html=getHtml(query);
        List<SubmmitStatus>list=SubmmitStatus.Builder.parse(html);
        return manager.map(list);
    }

    private String getHtml(String query){
        IRequest request=buildRequest(query);
        Logger.e(query);
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
