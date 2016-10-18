package thereisnospon.acclient.modules.note;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.data.NoteItem;
import thereisnospon.acclient.utils.LoadListCallback;

/**
 * Created by yzr on 16/9/9.
 */
public class NotePresenter implements NoteContact.Presenter {



    private NoteContact.Model model;
    private NoteContact.View view;

    public NotePresenter(NoteContact.View view) {
        this.view = view;
        model=new NoteModel();
    }

    @Override
    public void refreshNotes() {
        Observable.just(1)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, List<NoteItem>>() {
                    @Override
                    public List<NoteItem> call(Integer integer) {
                        return model.refreshNotes();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new LoadListCallback<List<NoteItem>>() {
                    @Override
                    public void onSuccess(List<NoteItem> noteItems) {
                        view.onRefreshNotes(noteItems);
                    }

                    @Override
                    public void onFailure(String err) {
                        view.onFailure(err);
                    }
                });
    }

    @Override
    public void moreNotes() {
        Observable.just(1)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, List<NoteItem>>() {
                    @Override
                    public List<NoteItem> call(Integer integer) {
                        return model.moreNotes();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new LoadListCallback<List<NoteItem>>() {
                    @Override
                    public void onSuccess(List<NoteItem> noteItems) {
                        view.onMoreNotes(noteItems);
                    }

                    @Override
                    public void onFailure(String err) {
                        view.onFailure(err);
                    }
                });
    }
}
