package thereisnospon.acclient.modules.problem_detail;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yzr on 16/6/6.
 */
public class ShowProblePresenter implements ShowProblemContact.Presenter {

    ShowProblemContact.View view;
    ShowProblemContact.Model model;

    public ShowProblePresenter(ShowProblemContact.View view) {
        this.view = view;
        model=new ShowProblemModel();
    }

    @Override
    public void loadProblemDetail(int id) {
        Observable.just(id)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return model.loadProblemDetail(integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        onResponse(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onFailure(throwable.getMessage());
                    }
                });
    }

    private void onResponse(String msg){
        if(msg!=null){
            view.onSuccess(msg);
        }else{
            view.onFailure("load error");
        }
    }
}
