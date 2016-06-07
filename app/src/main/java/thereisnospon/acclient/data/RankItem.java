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
public class  RankItem
{


    private int rank;
    private String nation;
    private String name;
    private String id;
    private String motto;
    private int solved;
    private int submission;

    public RankItem(int rank, String nation, String name, String id, String motto, int solved, int submission) {
        this.rank = rank;
        this.nation = nation;
        this.name = name;
        this.id = id;
        this.motto = motto;
        this.solved = solved;
        this.submission = submission;
    }


    public  static  class Builder
    {

        public static List<RankItem> parse(String html){
            Document document= Jsoup.parse(html);
            Element tbody=document.getElementsByTag("TBODY").first();
            Elements trs=tbody.getElementsByClass("table_text");
            List<RankItem>items=new ArrayList<>();
            for(int i=0;i<trs.size();i++){
                Element tr=trs.get(i);
                RankItem item=getRankItem(tr);
                if(item!=null&&item.valid()){
                    items.add(item);
                }
            }
            return items;
        }


        private static RankItem getRankItem(Element tr){
            Elements tds=tr.getElementsByTag("td");
            if(tds.size()<7)
                return null;
            int rank=Integer.parseInt(tds.get(0).text());
            String nation=getAttr("src",tds.get(1).html());
            String id=parseId(tds.get(2).html());
            String name=tds.get(2).text();
            String motto=tds.get(3).text();
            int solved=Integer.parseInt(tds.get(4).text());
            int submission=Integer.parseInt(tds.get(5).text());
            RankItem item=new RankItem(rank,nation,name,id,motto,solved,submission);
            return item;
        }


        private static final String ID_REGEX="\\?user=(\\S+)\"?\'?";


        private static String  parseId(String x){
            Pattern pattern=Pattern.compile(ID_REGEX);
            Matcher matcher=pattern.matcher(x);
            if(matcher.find()){
                return matcher.group(1);
            }else return null;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getSubmission() {
        return submission;
    }

    public void setSubmission(int submission) {
        this.submission = submission;
    }

    public boolean valid(){
        return rank>=1&&nation!=null&&name!=null&&id!=null&&motto!=null;
    }
}
