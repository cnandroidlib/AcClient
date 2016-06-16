package thereisnospon.acclient.modules.problem_list.search_list;

import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.data.ProblemItem;
import thereisnospon.acclient.data.SearchProblem;

/**
 * Created by yzr on 16/6/10.
 */
public class SearchProblemPresenter implements SearchProblemContact.Presenter {


    SearchProblemContact.View view;
    SearchProblemContact.Model model;

    public SearchProblemPresenter(SearchProblemContact.View view) {
        this.view = view;
        this.model=new SearchProblemModel();
    }

    @Override
    public void queryProblem(String query) {
        Log.d(TAG, "queryProblem: "+query);
        Observable.just(query)
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<SearchProblem>>() {
                    @Override
                    public List<SearchProblem> call(String s) {
                        return model.queryProblem(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SearchProblem>>() {
                    @Override
                    public void call(List<SearchProblem> searchProblems) {
                        if(searchProblems!=null&&searchProblems.size()>0){
                            view.refreshSuccess(searchProblems);
                            Log.d(TAG, "call: success"+searchProblems.size());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onFailure(throwable.getMessage());
                    }
                });
    }
    private static String TAG="SSEARCH";
    @Override
    public void loadMoreQuery( String query) {
        Log.d(TAG, "loadMoreQuery: "+query);
        Observable.just(query)
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<SearchProblem>>() {
                    @Override
                    public List<SearchProblem> call(String s) {
                        return model.loadMoreQuery(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SearchProblem>>() {
                    @Override
                    public void call(List<SearchProblem> searchProblems) {
                        if(searchProblems!=null&&searchProblems.size()>0){
                            view.loadSccess(searchProblems);
                            Log.d(TAG, "call: success "+searchProblems.size());
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
