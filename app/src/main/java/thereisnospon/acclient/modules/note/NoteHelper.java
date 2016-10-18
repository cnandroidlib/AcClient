package thereisnospon.acclient.modules.note;

import android.content.Context;
import android.content.Intent;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yzr on 16/9/16.
 */
public class NoteHelper  {


    public abstract class ReadNoteCall extends Subscriber<Intent>
    {
        @Override
        public void onCompleted() {

        }
    }

    public static void readNote(String pid,ReadNoteCall call){
        Observable.just(pid)
                .observeOn(Schedulers.io())
                .map(new Func1<String, Intent>() {
                    @Override
                    public Intent call(String s) {
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(call);
    }





}
