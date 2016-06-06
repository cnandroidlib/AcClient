package thereisnospon.acclient.data;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by yzr on 16/6/6.
 */
public class ProblemDetailBuilder {

    public static String parse(String html){

        Log.d("TTAG",html);
        Document document= Jsoup.parse(html);
        Elements trs=document.getElementsByTag("tr");
        Element tr=findTr(trs);
        Elements elements=tr.getElementsByTag("pre");
        for(int i=0;i<elements.size();i++){
            Element pre=elements.get(i);
            pre.html(pre.text());
        }
        Elements titles=tr.select(".panel_title");
        for(int i=0;i<titles.size();i++){
            Element div=titles.get(i);
            div.attr("style","color:#1A5CC8");
        }
        Elements contents=tr.select(".panel_content");
        for(int i=0;i<contents.size();i++){
            Element con=contents.get(i);
            con.before("<br>");
        }
        tr.removeClass(".panel_bottom");
        Elements imgs=tr.getElementsByTag("img");
        for(int i=0;i<imgs.size();i++){
            Element img=imgs.get(i);
            img.attr("src","http://acm.hdu.edu.cn/"+img.attr("src"));
        }
        Elements ttiles=tr.getElementsByTag("h1");
        for(int i=0;i<ttiles.size();i++)
            ttiles.get(i).attr("align","center");

        Elements as=tr.getElementsByTag("a");
        for(int i=as.size()-1;i>=as.size()-5&&i>=0;i--)
            as.get(i).remove();

        return tr.html();
    }

    public static Element findTr(Elements  elements){
        for(int i=0;i<elements.size();i++){
            Element element=elements.get(i);
            if(element.text().contains("Output"))
                return elements.get(i);
        }
        return null;
    }

}
