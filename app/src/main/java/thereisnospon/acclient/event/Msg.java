package thereisnospon.acclient.event;

import android.content.Context;
import android.widget.Toast;

import thereisnospon.acclient.AppApplication;

/**
 * Created by yzr on 16/8/21.
 */
public class Msg {

    public static void t(String msg){
        Toast.makeText( AppApplication.context,msg,Toast.LENGTH_SHORT).show();

    }
}
