package thereisnospon.acclient.modules.search_people;

import java.util.List;

import thereisnospon.acclient.data.SearchPeopleItem;

/**
 * Created by yzr on 16/6/16.
 */
public interface SearchPeopleContact {

    interface View{
        void refreshPeople(List<SearchPeopleItem>list);
        void loadMorePeople(List<SearchPeopleItem>list);
        void onFailure(String err);
    }
    interface Presenter{
        void searchPeople(String key);
        void loadMorePeople(String key);
    }
    interface Model{
        List<SearchPeopleItem>searchPeople(String key);
        List<SearchPeopleItem>loadMorePeople(String key);
    }
}
