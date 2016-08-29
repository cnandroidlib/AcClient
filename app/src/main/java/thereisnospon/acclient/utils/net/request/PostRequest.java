package thereisnospon.acclient.utils.net.request;

import android.util.Log;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yzr on 16/6/5.
 */

public class PostRequest extends IRequest {

    private FormBody.Builder parameter;

    public PostRequest(String url, OkHttpClient client){
        super(url,client);
    }

    @Override
    public IRequest addParameter(String key, String value) {
        if(isFisrtParamter){
            parameter=new FormBody.Builder();
            isFisrtParamter=false;
        }
        parameter.add(key,value);
        return this;
    }


    public IRequest addEncoded(String key,String value){
        if(isFisrtParamter){
            parameter=new FormBody.Builder();
            isFisrtParamter=false;
        }
        parameter.addEncoded(key,value);
        return this;
    }


    @Override
    protected Request buildRequest() {
        Request.Builder builder=new Request.Builder();
        initRequest(builder);
        postForm(builder);
        return builder.build();
    }

    private void postForm(Request.Builder builder){
        if(isFisrtParamter==false){
            Log.d("post", "postForm: ");
            builder.post(parameter.build());
        }
    }
}
