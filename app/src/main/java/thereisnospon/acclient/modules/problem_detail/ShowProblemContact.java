package thereisnospon.acclient.modules.problem_detail;

/**
 * Created by yzr on 16/6/6.
 */
public interface ShowProblemContact {

    public interface View{
        public void onSuccess(String html);
        public void onFailure(String msg);
    }

    public interface Presenter{
        public void loadProblemDetail(int id);
    }

    public interface Model{
        String loadProblemDetail(int id);
    }

}
