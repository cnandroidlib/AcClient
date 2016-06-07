package thereisnospon.acclient.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzr on 16/6/7.
 */
public class SearchProblem
{
    int id;
    String title;
    String author;
    String source;
    int accepted;
    int submission;

    public SearchProblem(int id, String title, String author, String source, int accepted, int submission) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.source = source;
        this.accepted = accepted;
        this.submission = submission;
    }

    public static class Builder{

        public static List<SearchProblem> parse(String html){
            Document document= Jsoup.parse(html);
            Element tbody=document.getElementsByTag("TBODY").first();
            Elements trs=tbody.getElementsByTag("tr");
            List<SearchProblem>list=new ArrayList<>();
            for(int i=0;i<trs.size();i++){
                Element tr=trs.get(i);
                if(tr.children().first().hasClass("TABLE_TEXT")){
                    SearchProblem problem=get(tr);
                    if(problem!=null){
                        list.add(problem);
                    }
                }
            }
            return list;
        }
        private static SearchProblem get(Element tr){

            Elements tds=tr.getElementsByTag("td");
            int id=Integer.parseInt(tds.get(1).text());
            String title=tds.get(2).text();
            String author=tds.get(3).text();
            String source=tds.get(4).text();
            String str=tds.get(5).text();
            String nums[]=str.split("[\\(\\)/%]");
            if(nums.length!=4)
                return null;
            int accepted=Integer.parseInt(nums[1]);
            int submission=Integer.parseInt(nums[2]);
            SearchProblem problem=new SearchProblem(id,title,author,source,accepted,submission);
            return problem;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getSubmission() {
        return submission;
    }

    public void setSubmission(int submission) {
        this.submission = submission;
    }

}