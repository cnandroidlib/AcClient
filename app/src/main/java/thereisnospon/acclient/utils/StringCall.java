package thereisnospon.acclient.utils;

import rx.Subscriber;

/**
 * Created by yzr on 16/8/22.
 */
public abstract class StringCall extends Subscriber<String> {

    public abstract void success(String nickName);
    public abstract void failure(String msg);

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        failure(e.getMessage());
    }

    @Override
    public void onNext(String s) {
        if(s!=null)
            success(s);
        else failure("null");
    }
}