package thereisnospon.acclient;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * Created by yzr on 16/6/5.
 */
public class AppApplication extends Application {

    public static Context  context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

}
