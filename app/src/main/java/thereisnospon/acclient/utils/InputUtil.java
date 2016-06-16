package thereisnospon.acclient.utils;

import org.jsoup.helper.StringUtil;

/**
 * Created by yzr on 16/6/10.
 */
public class InputUtil {

    public static boolean isValidProblemId(String str){
        if(!StringUtil.isNumeric(str)){
            return false;
        }else{
            long id=Long.parseLong(str);
            return id>=1000;
        }
    }

}
