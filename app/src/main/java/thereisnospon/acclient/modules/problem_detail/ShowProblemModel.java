package thereisnospon.acclient.modules.problem_detail;

import android.util.Log;

import java.io.IOException;

import okhttp3.Response;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.ProblemDetailBuilder;
import thereisnospon.acclient.utils.net.HttpUtil;

/**
 * Created by yzr on 16/6/6.
 */
public class ShowProblemModel implements ShowProblemContact.Model {

    @Override
    public String loadProblemDetail(int id) {
        String html=getHtml(id);
        if(html==null)
            return null;
        else
            return ProblemDetailBuilder.parse(html);

    }

    private String getHtml(int id){
        try{
            Response response= HttpUtil.getInstance()
                    .get(HdojApi.SHOW_PROBLEM)
                    .addParameter("pid",""+id)
                    .execute();
             String html=new String(response.body().bytes(),"gb2312");
            return html;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
