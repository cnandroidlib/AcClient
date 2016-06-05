package thereisnospon.acclient.utils.net.request;

import com.squareup.okhttp.Credentials;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import thereisnospon.acclient.utils.net.callback.NetCallback;

/**
 * Created by yzr on 16/6/5.
 */
public class AuthPostJsonRequest extends PostJsonRequest {

    public AuthPostJsonRequest(String url, OkHttpClient client){
        super(url,client);
    }

    @Override
    public IRequest enqueue(NetCallback callback) {
        client.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response)
                            throws IOException {
                        String credential= Credentials.basic(userName,password);
                        return response
                                .request()
                                .newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                });
        Request request=buildRequest();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return this;
    }

}
