package thereisnospon.acclient.modules.problem_list;

import java.util.List;

import thereisnospon.acclient.data.HdojProblem;

/**
 * Created by yzr on 16/6/5.
 */

public interface HdojContact {

    public interface View{
        void onMoreProblem(List<HdojProblem>list);
        public void  onSuccess(List<HdojProblem> list);
        public void  onFailure(String msg);
    }

    public interface Presenter{
        public void  loadMore();
        public void  refresh();
        public void  loadPage(int page);
    }
    public interface Model{
        public List<HdojProblem>loadMore() ;
        public List<HdojProblem>loadPage(int page);
        public List<HdojProblem>refresh();
    }
}
