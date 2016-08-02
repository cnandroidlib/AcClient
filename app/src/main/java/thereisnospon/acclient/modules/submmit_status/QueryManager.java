package thereisnospon.acclient.modules.submmit_status;

import java.util.List;

import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.SubmmitStatus;

/**
 * Created by yzr on 16/8/2.
 */
public class QueryManager {

    String first;
    String load(SubmmitQuery query){
        first=null;
        return HdojApi.JUDGE_STATUS+query;
    }
    String more(SubmmitQuery query){
        if(first==null)
            return HdojApi.JUDGE_STATUS+query;
        else return HdojApi.JUDGE_STATUS+"first="+first+query.query();
    }
    List<SubmmitStatus> map(List<SubmmitStatus>list){
        List<SubmmitStatus>ans;
        if(list==null||list.size()==0||(list.size()==1&&first!=null)){
            first=null;
            ans=null;
        }else{
            SubmmitStatus status=list.get(list.size()-1);
            if(first==null)
                ans=list;
            else ans=list.subList(1,list.size());
            first=status.getSubmmitId();
        }
        return ans;
    }
}
