package thereisnospon.acclient.modules.submmit_status;

import java.util.List;

import thereisnospon.acclient.data.SubmmitStatus;

/**
 * Created by yzr on 16/6/18.
 */
public interface SubmmitStatusContact {

    interface  View{
        void onMoreStatus(List<SubmmitStatus>statusList);
        void onRefreshStatus(List<SubmmitStatus>statusList);
        void onFailure(String err);
    }

    interface Presenter{
        void loadStatus(SubmmitQuery query);
        void moreStatus(SubmmitQuery query);
    }

    interface Model{
        List<SubmmitStatus>loadStatus(SubmmitQuery query);
        List<SubmmitStatus>moreStatus(SubmmitQuery query);
    }
}
