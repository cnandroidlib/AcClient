package thereisnospon.acclient.modules.submmit_status;

import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.data.SubmmitStatus;

/**
 * Created by yzr on 16/8/2.
 */
public class SubmmitStatuwPresenter implements SubmmitStatusContact.Presenter {

    SubmmitStatusContact.View view;
    SubmmitStatusContact.Model model;

    SubmmitStatuwPresenter(SubmmitStatusContact.View view){
        this.view=view;
        model=new SummitStatusModel();
    }

    @Override
    public void loadStatus(SubmmitQuery query) {
        Observable.just(query)
                .observeOn(Schedulers.io())
                .map(new Func1<SubmmitQuery, List<SubmmitStatus>>() {
                    @Override
                    public List<SubmmitStatus> call(SubmmitQuery query) {
                        return model.loadStatus(query);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SubmmitStatus>>() {
                    @Override
                    public void call(List<SubmmitStatus> statusList) {
                            if(statusList==null||statusList.size()==0){
                                view.onFailure("status empty");
                                Logger.d("empty");
                            }else{
                                view.onRefreshStatus(statusList);
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
    public void moreStatus(SubmmitQuery query) {
        Observable.just(query)
                .observeOn(Schedulers.io())
                .map(new Func1<SubmmitQuery, List<SubmmitStatus>>() {
                    @Override
                    public List<SubmmitStatus> call(SubmmitQuery query) {
                        return model.moreStatus(query);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SubmmitStatus>>() {
                    @Override
                    public void call(List<SubmmitStatus> statusList) {
                        if (statusList == null || statusList.size() == 0) {
                            view.onFailure("empty");
                            Logger.d("empty");
                        } else {
                            view.onMoreStatus(statusList);
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
