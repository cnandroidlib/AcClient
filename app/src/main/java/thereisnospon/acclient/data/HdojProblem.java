package thereisnospon.acclient.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojProblem
{
    int id;
    int solved;
    String title;
    int acceptedNum;
    int submissionNum;
    int tag;

    public HdojProblem( int solved,int id,int tag, String title, int acceptedNum, int submissionNum) {
        this.id = id;
        this.solved = solved;
        this.title = title;
        this.acceptedNum = acceptedNum;
        this.submissionNum = submissionNum;
        this.tag=tag;
    }

    public static class Builder
    {

        public static final String REGEX="p\\((-?\\d+),(-?\\d+),(-?\\d+),\"([\\s\\S]+?)\",(\\d+),(\\d+)";

        public static List<HdojProblem> buildProblems(String html){

            List<HdojProblem> list=new ArrayList<>();
            String content=html;
            Pattern pattern=Pattern.compile(REGEX);
            Matcher matcher=pattern.matcher(content);
            while (matcher.find()){
                int solved=Integer.parseInt(matcher.group(1));
                int id=Integer.parseInt(matcher.group(2));
                int tag=Integer.parseInt(matcher.group(3));
                String title= matcher.group(4);
                int acceptedNum=Integer.parseInt(matcher.group(5));
                int submissionNum=Integer.parseInt(matcher.group(6));
                HdojProblem problem=new HdojProblem(solved,id,tag,title,acceptedNum,submissionNum);
                list.add(problem);
            }
            return list;
        }

        private static String getContent(String html){
            Document document= Jsoup.parse(html);
            if(document.hasClass("table_text")){
                Element table=document.getElementsByClass("table_text").first();
                return table.html();
            }else{
                return "";
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAcceptedNum() {
        return acceptedNum;
    }

    public void setAcceptedNum(int acceptedNum) {
        this.acceptedNum = acceptedNum;
    }

    public int getSubmissionNum() {
        return submissionNum;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setSubmissionNum(int submissionNum) {
        this.submissionNum = submissionNum;
    }

}
