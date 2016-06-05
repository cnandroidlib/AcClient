package thereisnospon.acclient.utils.net.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by yzr on 16/6/5.
 */
public  abstract class StringCallback  extends NetCallback{

    public abstract void onFail(String msg);
    public abstract void onSuccess(String str, Headers headers);
    @Override
    public void onFailure(Call call, IOException e) {
        onFail(e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        if(!response.isSuccessful()){
            onFail("fail"+response.code());
        }else{
            onSuccess(response.body().string(),response.headers());
        }
    }
}
