package thereisnospon.acclient.utils;

import android.util.Base64;

/**
 * Created by yzr on 16/6/16.
 */


public class EncodeUtils {

    public static String encodeToBase64(String str){
        return Base64.encodeToString(str.getBytes(),Base64.DEFAULT);
    }


}
