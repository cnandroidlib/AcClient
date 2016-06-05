package thereisnospon.acclient.utils.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yzr on 16/6/5.
 */
public abstract  class JsonCallback<T> extends NetCallback{

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
        onError("IO EXCEPTION");
    }

    public abstract void onError(String err);

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String str=response.body().string();
        Log.d("Resoponse",str);
        try{
            T entity = new Gson().fromJson(str,((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            onSuccess(entity,response);
        }catch (JsonSyntaxException e){
            onError("json err");
        }
    }
    public abstract void onSuccess(T entrity,Response response);
}
