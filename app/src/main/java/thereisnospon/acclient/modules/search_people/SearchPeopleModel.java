package thereisnospon.acclient.modules.search_people;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.SearchPeopleItem;
import thereisnospon.acclient.data.SearchProblem;
import thereisnospon.acclient.modules.problem_list.search_list.SearchProblemContact;
import thereisnospon.acclient.utils.net.HttpUtil;
import thereisnospon.acclient.utils.net.request.IRequest;

/**
 * Created by yzr on 16/6/16.
 */
public class SearchPeopleModel implements SearchPeopleContact.Model {

    public static final int PRE_PAGE_NUM=30;
    private List<SearchPeopleItem> peopleItems;
    private int current=0;
    private String key;
    private final String TAG="searchpeople";

    private boolean isLoaded(String key){
        return this.key!=null&&this.key.equals(key)&&peopleItems!=null&&peopleItems.size()>0;
    }

    private boolean hasMore(){
        return peopleItems!=null&&current<peopleItems.size();
    }

    private void reset(String key,List<SearchPeopleItem>list){
        this.key=key;
        this.current=0;
        this.peopleItems=list;
    }


    private List<SearchPeopleItem>loadMore(){
        List<SearchPeopleItem>list=new ArrayList<>();
        for(int i=0;i<PRE_PAGE_NUM&&current<peopleItems.size();i++,current++){
            list.add(peopleItems.get(i));
        }
        return peopleItems;
    }

    @Override
    public List<SearchPeopleItem> searchPeople(String key) {
        String html=getHtml(key);
        List<SearchPeopleItem>list=SearchPeopleItem.Builder.parse(html);
        if(list!=null){
            reset(key,list);
            return loadMore();
        }
        return null;
    }

    @Override
    public List<SearchPeopleItem> loadMorePeople(String key) {
        if(isLoaded(key)){
            return loadMore();
        }else{
            return searchPeople(key);
        }
    }

    private String getHtml(String key){
        try{
            IRequest request= HttpUtil.getInstance()
                    .get(HdojApi.SEARCH_PEOPLE)
                    .addParameter("field","author")
                    .addParameter("key",key);
            Response response=request.execute();
            Log.d(TAG, "getHtml: key:"+key);
            String html=new String(response.body().bytes(),"gb2312");
            Log.d(TAG, "getHtml: "+html);
            return html;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
