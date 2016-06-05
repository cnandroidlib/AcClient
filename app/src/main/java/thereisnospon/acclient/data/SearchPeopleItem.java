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
 * Created by yzr on 16/6/5.
 */
public class SearchPeopleItem
{
    int rank;
    String nation;
    String id;
    String name;
    String school;
    int accepted;

    public SearchPeopleItem(int rank, String nation, String id, String name, String school, int accepted) {
        this.rank = rank;
        this.nation = nation;
        this.id = id;
        this.name = name;
        this.school = school;
        this.accepted = accepted;
    }

    public static class  SearchItemBuilder{

        public static List<SearchPeopleItem> parse(String html){
            Document document= Jsoup.parse(html);
            //System.out.println(document.html());
            Element table=document.getElementsByClass("TABLE_TEXT").first();
            //System.out.println(table.html());
            Elements trs=table.getElementsByTag("tr");
            List<SearchPeopleItem>list=new ArrayList<>();
            for(int i=1;i<trs.size();i++ ){
                Element tr=trs.get(i);
                SearchPeopleItem item=getItem(tr);
                if(item!=null&&item.isValid()){
                    list.add(item);
                }
            }
            return list;
        }


        public static SearchPeopleItem getItem(Element element){
            Elements tds=element.getElementsByTag("td");
            if(tds.size()<0)
                return null;
            int rank=Integer.parseInt(tds.get(0).text());
            String nation=getAttr("src",tds.get(1).html());
            String id=tds.get(2).text();
            String name=tds.get(3).text();
            String school=tds.get(4).text();
            int accepted=Integer.parseInt(tds.get(5).text());
            return new SearchPeopleItem(rank,nation,id,name,school,accepted);


        }


        public static String getAttr(String key,String html){

            String regex=key+"=[\"']+?(\\S+)[\"']+?";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(html);
            if(matcher.find())
                return matcher.group(1);
            else return null;
        }




    }




    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public boolean isValid(){
        return rank>0&&nation!=null&&name!=null&&id!=null&&school!=null;
    }
}
