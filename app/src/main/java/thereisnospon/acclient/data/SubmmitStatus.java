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
 * Created by yzr on 16/6/8.
 */
public class SubmmitStatus
{
    String submmitId;
    String date;
    String status;
    String problemId;
    String usedTime;
    String usedMemory;
    String codeLen;
    String language;
    String userName;
    String userId;




    public SubmmitStatus(String submmitId, String date, String status,
                         String problemId, String usedTime, String usedMemory,
                         String codeLen, String language, String userName, String userId) {
        this.submmitId = submmitId;
        this.date = date;
        this.status = status;
        this.problemId = problemId;
        this.usedTime = usedTime;
        this.usedMemory = usedMemory;
        this.codeLen = codeLen;
        this.language = language;
        this.userName = userName;
        this.userId = userId;
    }



    public static class Builder
    {

        public static List<SubmmitStatus> parse(String html){
            Document document= Jsoup.parse(html);
            Element div=document.getElementById("fixed_table");
            Element table=div.getElementsByTag("table").first();
            Elements trs=table.getElementsByTag("tr");
            List<SubmmitStatus>list=new ArrayList<>();

            for(int i=1;i<trs.size();i++){
                SubmmitStatus status=get(trs.get(i).getElementsByTag("td"));
                if(status!=null)
                    list.add(status);
            }
            return list;

        }



        private  static SubmmitStatus get(Elements tds){

            if(tds.size()!=9)
                return null;
            String submmitedId=tds.get(0).text();
            String date=tds.get(1).text();
            String status=tds.get(2).text();
            String problemId=tds.get(3).text();
            String time=tds.get(4).text();
            String memory=tds.get(5).text();
            String codeLen=tds.get(6).text();
            String language=tds.get(7).text();
            String userName=tds.get(8).text();
            String userId=parseId(tds.get(8).html());

            SubmmitStatus submmitStatus=new SubmmitStatus(
                    submmitedId,date,status,problemId,
                    time,memory,codeLen,language
                    ,userName,userId
            );

            return submmitStatus;

        }

        private static final String ID_REGEX="\\?user=(\\S+)[\"']+?";

        private static String  parseId(String x){
            Pattern pattern=Pattern.compile(ID_REGEX);
            Matcher matcher=pattern.matcher(x);
            if(matcher.find()){
                return matcher.group(1);
            }else return "";
        }
    }

    public String getSubmmitId() {
        return submmitId;
    }

    public void setSubmmitId(String submmitId) {
        this.submmitId = submmitId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }

    public String getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory;
    }

    public String getCodeLen() {
        return codeLen;
    }

    public void setCodeLen(String codeLen) {
        this.codeLen = codeLen;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private static String _tag(String key, String value){
        return key+":\t"+value+"\n";
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append(_tag("submmitId",submmitId));
        builder.append(_tag("date",date));
        builder.append(_tag("status",status));
        builder.append(_tag("problemId",problemId));
        builder.append(_tag("usedTime",usedTime));
        builder.append(_tag("usedMemory",usedMemory));
        builder.append(_tag("codelen",codeLen));
        builder.append(_tag("language",language));
        builder.append(_tag("userName",userName));
        builder.append(_tag("userId",userId));
        return builder.toString();
    }
}