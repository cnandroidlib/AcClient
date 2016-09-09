package thereisnospon.acclient.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yzr on 16/9/8.
 */
public class DiscussItem{


    private String title;
    private String discussUrl;
    private String userUrl;
    private String pidUrl;
    private String userName;
    private String date;


    public DiscussItem(String title, String discussUrl, String userUrl,
                       String pidUrl, String userName, String date) {
        this.title = title;
        this.discussUrl = discussUrl;
        this.userUrl = userUrl;
        this.pidUrl = pidUrl;
        this.userName = userName;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPid(){
        return getLast(pidUrl);
    }

    public String getUser(){
        return getLast(userUrl);
    }

    private String getLast(String str){
        if(str==null)
            return null;
        String temps[]=str.split("=");
        return temps!=null&&temps.length>=2?temps[1]:null;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscussUrl() {
        return discussUrl;
    }

    public void setDiscussUrl(String discussUrl) {
        this.discussUrl = discussUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getPidUrl() {
        return pidUrl;
    }

    public void setPidUrl(String pidUrl) {
        this.pidUrl = pidUrl;
    }
    public static class Builder{
        public static List<DiscussItem>parse(String html){
            if(html==null)
                return null;
            List<DiscussItem>items=new ArrayList<>();
            Document document=Jsoup.parse(html);
            Elements uls=document.getElementsByClass("v");
            for(int i=0;i<uls.size();i++){
                DiscussItem item=getItem(uls.get(i));
                if(item!=null){
                    items.add(item);
                }
            }
            return items;
        }
        private static DiscussItem getItem(Element ul){
            if(ul.id()==null||!ul.id().equals("ulframe")){
                return null;
            }
            Elements a=ul.getElementsByTag("a");
            if(a.size()<=2)
                return null;
            String useranme=getUserName(ul);
            String date=getDate(ul.text());
            String title=a.get(0).text();
            String url=a.get(0).attr("href");
            String statusurl=a.get(1).attr("href");
            String pidurl=a.get(2).attr("href");
            DiscussItem item=new DiscussItem(title,url,statusurl,pidurl,useranme,date);
            return item;
        }
        private static String DATE_REGREX="\\d+-\\d+-\\d+ \\d+:\\d+:\\d+";

        private static Pattern pattern;
        static {
            pattern= Pattern.compile(DATE_REGREX);
        }

        private static String getDate(String x){
            Matcher matcher=pattern.matcher(x);
            return matcher.find()?matcher.group(0):null;
        }

        private static String getUserName(Element element){
            Elements elements=element.getElementsByTag("font");
            for(int i=0;i<elements.size();i++){
                Element font=elements.get(i);
                String color=font.attr("color");
                if("#001111".equals(color)){
                    Elements s=font.getElementsByTag("b");
                    if(s!=null&&s.size()>0){
                        return s.first().text();
                    }
                }
            }
            return "unknow";
        }
    }
}