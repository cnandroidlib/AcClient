package thereisnospon.acclient.utils.net.request;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by yzr on 16/6/5.
 */
public class PostJsonRequest extends PostRequest {

    String userName;
    String password;
    private String json;

    public static final MediaType Json = MediaType.parse("application/json; charset=utf-8");

    public PostJsonRequest(String url, OkHttpClient client){
        super(url,client);
    }
    @Override
    public IRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public IRequest setPassword(String password) {
        this.password = password;
        return this;
    }



    @Override
    public PostRequest addJsonObject(Object object){
        this.json=new Gson().toJson(object);
        return this;
    }


    @Override
    protected Request buildRequest() {
        Request.Builder builder=new Request.Builder();
        initRequest(builder);
        RequestBody body=RequestBody.create(Json,this.json);
        builder.post(body);
        return  builder.build();
    }

}
