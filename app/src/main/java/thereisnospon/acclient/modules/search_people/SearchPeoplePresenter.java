package thereisnospon.acclient.modules.search_people;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.data.SearchPeopleItem;
import thereisnospon.acclient.modules.problem_list.search_list.SearchProblemContact;

/**
 * Created by yzr on 16/6/16.
 */
public class SearchPeoplePresenter implements SearchPeopleContact.Presenter {

    private SearchPeopleContact.Model model;
    private SearchPeopleContact.View  view;

    public SearchPeoplePresenter(SearchPeopleContact.View view) {
       this.view=view;
        this.model=new SearchPeopleModel();
    }

    @Override
    public void searchPeople(String key) {
        Observable.just(key)
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<SearchPeopleItem>>() {
                    @Override
                    public List<SearchPeopleItem> call(String s) {
                        return model.searchPeople(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SearchPeopleItem>>() {
                    @Override
                    public void call(List<SearchPeopleItem> list) {
                        if(list!=null&&list.size()>0){
                            view.refreshPeople(list);
                        }else{
                            view.onFailure("empty");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onFailure(throwable.getMessage());
                    }
                });
    }

    @Override
    public void loadMorePeople(String key) {
        Observable.just(key)
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<SearchPeopleItem>>() {
                    @Override
                    public List<SearchPeopleItem> call(String s) {
                        return model.loadMorePeople(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SearchPeopleItem>>() {
                    @Override
                    public void call(List<SearchPeopleItem> list) {
                        if(list!=null&&list.size()>0){
                            view.loadMorePeople(list);
                        }else{
                            view.onFailure("empty");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onFailure(throwable.getMessage());
                    }
                });
    }
}
