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
 * Created by yzr on 16/6/7.
 */
public class UserInfo
{

    String name;
    String school;
    String  regsiterDate;
    String des;
    String nation;
    int rank;
    int submitted;
    int solved;
    int submission;
    int accepted;

    List<Node> acceptedNodeList;
    List<Node>unacceptedNodeList;

    UserInfo(){

    }

    public static class Node{
        int id;
        int tag1;
        int tag2;

        public Node(int id, int tag1, int tag2) {
            this.id = id;
            this.tag1 = tag1;
            this.tag2 = tag2;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTag1() {
            return tag1;
        }

        public void setTag1(int tag1) {
            this.tag1 = tag1;
        }

        public int getTag2() {
            return tag2;
        }

        public void setTag2(int tag2) {
            this.tag2 = tag2;
        }
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

    public String getRegsiterDate() {
        return regsiterDate;
    }

    public void setRegsiterDate(String regsiterDate) {
        this.regsiterDate = regsiterDate;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getSubmitted() {
        return submitted;
    }

    public void setSubmitted(int submitted) {
        this.submitted = submitted;
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

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }


    public List<Node> getAcceptedNodeList() {
        return acceptedNodeList;
    }

    public void setAcceptedNodeList(List<Node> acceptedNodeList) {
        this.acceptedNodeList = acceptedNodeList;
    }

    public List<Node> getUnacceptedNodeList() {
        return unacceptedNodeList;
    }

    public void setUnacceptedNodeList(List<Node> unacceptedNodeList) {
        this.unacceptedNodeList = unacceptedNodeList;
    }

    public List<Integer>getAcceptedIds(){
        List<Integer>list=new ArrayList<>();
        for(Node node:acceptedNodeList)
            list.add(node.getId());
        return list;

    }
    public List<Integer>getUnacceptedIds(){
        List<Integer>list=new ArrayList<>();
        for(Node node:unacceptedNodeList)
            list.add(node.getId());
        return list;
    }



    @Override
    public String toString(){
        StringBuilder builder=new StringBuilder();
        builder.append("name:\t"+name+"\n");
        builder.append("school:\t"+school+"\n");
        builder.append("date:\t"+regsiterDate+"\n");
        builder.append("des:\t"+des+"\n");
        builder.append("nation:\t"+name+"\n");
        builder.append("rank:\t"+rank+"\n");
        builder.append("submitted:\t"+submitted+"\n");
        builder.append("solved:\t"+solved+"\n");
        builder.append("submission:\t"+submission+"\n");
        builder.append("accepted:\t"+accepted+"\n");
        for(Node node:acceptedNodeList)
            builder.append("num:"+node.getId());
        builder.append("\n");
        for(Node node:unacceptedNodeList)
            builder.append("un:"+node.getId());
        builder.append("\n");
        return builder.toString();
    }

    public static class Builder
    {

        public static UserInfo parse(String html){
            UserInfo userInfo=new UserInfo();

            Document document= Jsoup.parse(html);
            Element TD=getTD(document);

            userInfo.setName(TD.getElementsByTag("h1").first().text());
            String p=TD.getElementsByTag("i").first().text();
            userInfo.setSchool(getSchool(p));
            userInfo.setRegsiterDate(getDate(p));
            userInfo.setDes(TD.getElementsByTag("p").first().text());

            step1(userInfo,TD.getElementsByTag("table").first());
            Elements scripts=TD.getElementsByTag("script");

            List<UserInfo.Node>acnodelist=getNodeList(scripts.get(0).html());
            userInfo.setAcceptedNodeList(acnodelist);
            List<UserInfo.Node>unaclist=getNodeList(scripts.get(1).html());
            userInfo.setUnacceptedNodeList(unaclist);

            return userInfo;
        }



        public static List<UserInfo.Node>getNodeList(String ps){
            String regex="p\\((\\d+),(\\d+),(\\d+)\\)";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(ps);
            List<UserInfo.Node>list=new ArrayList<>();
            while (matcher.find()){
                int id=Integer.parseInt(matcher.group(1));
                int t1=Integer.parseInt(matcher.group(2));
                int t2=Integer.parseInt(matcher.group(3));
                UserInfo.Node node=new UserInfo.Node(id,t1,t2);
                list.add(node);
            }
            return list;
        }

        public static void setAccepted(UserInfo userInfo,String ps){
            String regex="p\\((\\d+),(\\d+),(\\d+)\\)";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(ps);
            System.out.print(ps);
        }


        public static void step1(UserInfo userInfo,Element table){
            Elements tds=table.getElementsByTag("td");

            userInfo.setNation(getAttr("src",tds.get(1).html()));
            userInfo.setRank(Integer.parseInt(tds.get(3).text()));
            userInfo.setSubmitted(Integer.parseInt(tds.get(5).text()));
            userInfo.setSolved(Integer.parseInt(tds.get(7).text()));
            userInfo.setSubmission(Integer.parseInt(tds.get(9).text()));
            userInfo.setAccepted(Integer.parseInt(tds.get(11).text()));

        }


        public static String getAttr(String key,String html){
            String regex=key+"=[\"']+?(\\S+)[\"']+?";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(html);
            if(matcher.find())
                return matcher.group(1);
            else return null;
        }


        public static String getSchool(String s){
            String regex="from:\\s+(\\S+)regi";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(s);
            if(matcher.find()){
                return matcher.group(1);
            }
            return "";
        }


        public static String getDate(String s){
            String regex="(\\d+-\\d+-\\d+)";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(s);
            if (matcher.find()){
                return matcher.group(1);
            }

            else return "";
        }

        public static Element getTD(Element element){
            Elements tds=element.getElementsByTag("TD");
            for(int i=0;i<tds.size();i++){
                Element td=tds.get(i);
                Elements chis=td.children();
                if(chis!=null && chis.size()>0
                        &&chis.first().tagName().equals("h1")
                        &&td.html().contains("Rank")){
                    return td;
                }
            }
            return null;
        }



    }

}
