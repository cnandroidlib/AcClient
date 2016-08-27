package thereisnospon.acclient.modules.rank;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.RankItem;
import thereisnospon.acclient.utils.net.HttpUtil;
import thereisnospon.acclient.utils.net.request.IRequest;

/**
 * Created by yzr on 16/8/27.
 */
public class RankModel implements RankContact.Model {



    private int currentPage=1;

    public RankModel() {
        currentPage=1;
    }



    @Override
    public List<RankItem> loadRankItems() {
        currentPage=1;
        return getRanks(currentPage);
    }

    @Override
    public List<RankItem> moreRankItems() {
        return getRanks(currentPage);
    }


    private List<RankItem>getRanks(int page){
        String html=getHtml(page);
        if(html==null)
            return null;
        List<RankItem>rankItems=RankItem.Builder.parse(html);
        if(rankItems!=null&&rankItems.size()!=0){
            currentPage=page+1;
        }
        return rankItems;
    }

    private String getHtml(int page){
        int from=(page-1)*25+1;
        IRequest request=HttpUtil.getInstance()
                .get(HdojApi.RANK)
                .addParameter("from",""+from);
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
