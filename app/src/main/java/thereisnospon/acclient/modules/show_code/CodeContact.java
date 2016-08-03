package thereisnospon.acclient.modules.show_code;

/**
 * Created by yzr on 16/8/2.
 */
public interface CodeContact {
    interface View{
        void onSuccess(String code);
        void onFailure(String err);
    }
    interface Model{
        String loadCode(String id);
    }
    interface Presenter{
        void loadCode(String id);
    }
}
