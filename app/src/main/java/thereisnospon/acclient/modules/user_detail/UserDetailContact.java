package thereisnospon.acclient.modules.user_detail;

import thereisnospon.acclient.data.UserInfo;

/**
 * Created by yzr on 16/6/18.
 */
public interface UserDetailContact  {
    interface View{
        void onSuccess(UserInfo userInfo);
        void onError(String err);
    }
    interface Presenter{
        void loadUser(String id);
    }
    interface Model{
        UserInfo loadUser(String id);
    }
}
