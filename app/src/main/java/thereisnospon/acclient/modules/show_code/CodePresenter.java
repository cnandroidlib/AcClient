package thereisnospon.acclient.modules.show_code;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yzr on 16/8/2.
 */
public class CodePresenter implements CodeContact.Presenter {


    CodeContact.View view;
    CodeContact.Model model;

    CodePresenter(CodeContact.View view){
        this.view=view;
        model=new CodeModel();
    }
    @Override
    public void loadCode(final String id) {
        Observable.just(id)
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return model.loadCode(id);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        view.onSuccess(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onFailure(throwable.getMessage());
                    }
                });

    }
}
