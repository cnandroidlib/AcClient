package thereisnospon.acclient.modules.user_detail;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.data.UserInfo;

/**
 * Created by yzr on 16/6/18.
 */
public class UserDetailPresenter implements UserDetailContact.Presenter {

    UserDetailContact.View view;
    UserDetailContact.Model model;

    public UserDetailPresenter(UserDetailContact.View view) {
        this.view = view;
        this.model=new UserDetailModel();
    }

    @Override
    public void loadUser(String id) {
        Observable.just(id)
                .observeOn(Schedulers.io())
                .map(new Func1<String, UserInfo>() {
                    @Override
                    public UserInfo call(String s) {
                        return model.loadUser(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfo>() {
                    @Override
                    public void call(UserInfo userInfo) {
                        if(userInfo!=null){
                            view.onSuccess(userInfo);
                        }else{
                            view.onError("err");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onError(throwable.getMessage());
                    }
                });
    }
}
