package thereisnospon.acclient.data;

import android.util.Log;

import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by yzr on 16/8/2.
 */
public class CodeBuilder {
    public static String parse(String html){
        Document document= Jsoup.parse(html);
        Element element=getTexrs(document);
        if(element!=null)
            return element.text();
        else return "";

    }
    private static Element getTexrs(Document document){
        Elements elements=document.getElementsByTag("textarea");
        Logger.d(elements.first().text());
        if(elements==null||elements.size()==0)
            return null;
        else return elements.first();
    }

}
