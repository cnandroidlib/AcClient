package thereisnospon.acclient.modules.discuss;

import java.util.List;

import thereisnospon.acclient.data.DiscussItem;

/**
 * Created by yzr on 16/9/9.
 */
public interface DiscussContact {
    interface View{
        void onRefreshDiscuss(List<DiscussItem>discussItems);
        void onMoreDiscuss(List<DiscussItem>discussItems);
        void onFailure(String err);
    }
    interface Presenter{
        void refreshDiscuss();
        void moreDiscuss();
    }
    interface Model{
        List<DiscussItem>refreshDiscuss();
        List<DiscussItem>moreDisucss();
    }
}
