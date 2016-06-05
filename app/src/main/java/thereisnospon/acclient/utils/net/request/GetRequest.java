package thereisnospon.acclient.utils.net.request;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yzr on 16/6/5.
 */
public class GetRequest extends IRequest {

    public GetRequest(String url, OkHttpClient client){
        super(url,client);
    }

    @Override
    public IRequest addParameter(String key, String value) {
        if(isFisrtParamter){
            url+="?";
            isFisrtParamter=false;
        }else{
            url+="&";
        }
        url+=key+"="+value;
        return this;
    }

    @Override
    protected Request buildRequest() {
        Request.Builder builder=new Request.Builder();
        initRequest(builder);
        builder.get();
        return builder.build();
    }
}
