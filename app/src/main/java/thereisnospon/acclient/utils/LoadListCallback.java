package thereisnospon.acclient.utils;

import java.util.List;

import rx.Subscriber;

/**
 * Created by yzr on 16/9/9.
 */
public abstract class LoadListCallback<T extends List> extends Subscriber<T> {

    public abstract void onSuccess(T t);
    public abstract void onFailure(String err);

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFailure(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        if(t!=null&&t.size()>0)
            onSuccess(t);
        else{
            onFailure("empty list");
        }
    }
}