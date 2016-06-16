package thereisnospon.acclient.modules.problem_list.search_list;

import java.util.List;

import thereisnospon.acclient.data.SearchProblem;

/**
 * Created by yzr on 16/6/10.
 */
public interface SearchProblemContact {


    interface View{
        void loadSccess(List<SearchProblem>list);
        void refreshSuccess(List<SearchProblem>list);
        void onFailure(String errMsg);
    }

    interface Presenter{
         void queryProblem(String query);
         void loadMoreQuery(String query);
    }

    interface Model{
        List<SearchProblem> queryProblem(String query);
        List<SearchProblem>loadMoreQuery(String query);
    }

}
