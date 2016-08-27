package thereisnospon.acclient.modules.rank;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.data.RankItem;

/**
 * Created by yzr on 16/8/27.
 */
public class RankPresenter implements RankContact.Presenter {


    RankContact.View view;

    RankContact.Model model;

    public RankPresenter(RankContact.View view) {
        this.view = view;
        model=new RankModel();
    }

    @Override
    public void loadRankItems() {
        Observable.just(1)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, List<RankItem>>() {
                    @Override
                    public List<RankItem> call(Integer integer) {
                        return model.loadRankItems();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<RankItem>>() {
                    @Override
                    public void call(List<RankItem> list) {
                        if (list !=null &&list.size()!=0)
                            view.onRefreshRankSuccess(list);
                        else view.onRankFailure("load null");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onRankFailure(throwable.getMessage());
                    }
                });
    }

    @Override
    public void moreRankItems() {
        Observable.just(1)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, List<RankItem>>() {
                    @Override
                    public List<RankItem> call(Integer integer) {
                        return model.moreRankItems();
                }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<RankItem>>() {
                    @Override
                    public void call(List<RankItem> list) {
                        if (list !=null &&list.size()!=0)
                            view.onMoreRanks(list);
                        else view.onRankFailure("load null");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.onRankFailure(throwable.getMessage());
                    }
                });
    }
}
