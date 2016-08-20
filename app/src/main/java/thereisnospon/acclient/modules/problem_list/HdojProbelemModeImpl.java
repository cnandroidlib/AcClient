package thereisnospon.acclient.modules.problem_list;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.HdojProblem;
import thereisnospon.acclient.utils.net.HttpUtil;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojProbelemModeImpl implements HdojContact.Model {

    int currentPage=1;
    int mloadPge=-1;


    public HdojProbelemModeImpl(int page){
        this.mloadPge=page;
    }

    @Override
    public List<HdojProblem> loadPage(int page) {
        String html=getHtml(page);
        List<HdojProblem>list= HdojProblem.Builder.buildProblems(html);
        if(list!=null&&list.size()>0){
            this.currentPage=page;
        }
        return list;
    }

    @Override
    public List<HdojProblem> loadMore() {
        if(mloadPge!=-1)
            return null;
        String html=getHtml(currentPage+1);
        List<HdojProblem>list= HdojProblem.Builder.buildProblems(html);
        if(list!=null&&list.size()>0){
            currentPage++;
        }
        return list;
    }

    private String getHtml(int page){
        try{
            Response response= HttpUtil.getInstance()
                    .get(HdojApi.PROBLEM_LIST)
                    .addParameter("vol",""+page)
                    .execute();
            String html=  new String(response.body().bytes(),"gb2312");
            return html;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public List<HdojProblem> refresh() {
        if(mloadPge!=-1){
            return loadPage(mloadPge);
        }else{
            return loadPage(1);
        }
    }
}
