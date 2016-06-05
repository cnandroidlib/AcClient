package thereisnospon.acclient.utils.net.callback;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yzr on 16/6/5.
 */
public abstract class StreamCallback extends NetCallback {

    @Override
    public void onFailure(Call call, IOException e) {
        onFailure(e.getMessage());
    }
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(!response.isSuccessful()){
            onFailure("err:"+response.code());
            Log.d("Download", "onResponse: "+"err");
        }else{
            InputStream stream=response.body().byteStream();
            onStream(stream);
            Log.d("Download", "onResponse: "+"success steam");
        }
    }
    public abstract void onFailure(String msg);
    public abstract void onStream(InputStream stream);

}
