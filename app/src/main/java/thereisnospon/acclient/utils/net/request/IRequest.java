package thereisnospon.acclient.utils.net.request;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import thereisnospon.acclient.utils.net.callback.NetCallback;

/**
 * Created by yzr on 16/6/5.
 */

public abstract class IRequest {

    protected String url;
    protected Map<String ,String > headers;
    protected boolean isFisrtParamter;
    protected Object tag;

    protected OkHttpClient client;



    public IRequest(){
        this(null,null);
    }

    public IRequest(String url, OkHttpClient client){
        this.url=url;
        isFisrtParamter=true;
        this.client=client;
    }

    public IRequest setTag(Object tag){
        this.tag=tag;
        return this;
    }


    public IRequest addJsonObject(Object object){
        throw new UnsupportedOperationException("can't add json object");
    }
    public IRequest setUserName(String userName){
        throw new UnsupportedOperationException("can't set userName");

    }
    public IRequest setPassword(String password){
        throw new UnsupportedOperationException("can't set password");
    }

    public abstract IRequest addParameter(String key,String value);

    public IRequest addHeader(String key,String value){
        if(headers==null){
            headers=new LinkedHashMap<>();
        }
        headers.put(key,value);
        return this;
    }

    public Response execute()throws IOException {
        Call call=client.newCall(buildRequest());
        Response response=call.execute();
        return response;
    }

    public  IRequest enqueue(NetCallback callback){
        Request request=buildRequest();
        Call call= client.newCall(request);
        call.enqueue(callback);
        return this;
    }

    protected abstract  Request buildRequest();

    protected void initRequest(Request.Builder builder){
        builder.url(url);
        buildHeaders(builder);
        buildTag(builder);
    }

    protected void buildHeaders(Request.Builder builder){
        if(headers!=null){
            for(Map.Entry<String,String> entry:headers.entrySet()){
                builder.addHeader(entry.getKey(),entry.getValue());
            }
        }
    }

    protected void buildTag(Request.Builder builder){
        if(tag!=null){
            builder.tag(tag);
        }
    }

}
