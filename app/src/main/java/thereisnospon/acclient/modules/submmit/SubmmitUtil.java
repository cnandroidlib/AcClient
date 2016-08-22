package thereisnospon.acclient.modules.submmit;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thereisnospon.acclient.api.HdojApi;
import thereisnospon.acclient.data.SubmmitStatus;
import thereisnospon.acclient.utils.StringCall;
import thereisnospon.acclient.utils.net.HttpUtil;

/**
 * Created by yzr on 16/8/20.
 */
public class SubmmitUtil {



    public static final String CODE="import java.util.*;\n" +
            "import java.math.*;\n" +
            "import java.io.*;\n" +
            "public class Main {\n" +
            "\n" +
            "    public static void main(String []args)\n" +
            "    {\n" +
            "        String sa,sb;\n" +
            "        Scanner cin=new Scanner(System.in);\n" +
            "        while(cin.hasNext())\n" +
            "        {\n" +
            "            sa=cin.next();\n" +
            "            sb=cin.next();\n" +
            "            BigInteger x=new BigInteger(sa);\n" +
            "            BigInteger y=new BigInteger(sb);\n" +
            "            System.out.println(x.add(y).toString());\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}";



    public static void submmit(
            final String problem, final String compiler,
            final String code, final StringCall call) {

        Observable.just(1)
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return getHtml(problem,compiler,code);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(call);

    }



    public static String getHtml(String problem,String language,String code){
        try{
            Response response=HttpUtil.getInstance()
                    .post(HdojApi.SUBMMIT)
                    .addParameter("check","0")
                    .addParameter("problemid",problem)
                    .addParameter("language",language)
                    .addParameter("usercode",code)
                    .execute();
            String html=new String(response.body().bytes(),"gb2312");
            return html;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }




}
