package thereisnospon.acclient.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzr on 16/9/9.
 */
public class NoteItem
{
    private String nid;
    private String pid;
    private String title;
    private String date;

    public NoteItem(String nid, String pid, String title, String date) {
        this.nid = nid;
        this.pid = pid;
        this.title = title;
        this.date = date;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class Builder{
        public static List<NoteItem> parse(String html){
            if(html==null)
                return null;
            List<NoteItem>noteItems=new ArrayList<>();
            Element note= Jsoup.parse(html).getElementById("notes");
            Elements trs=note.getElementsByTag("tr");
            for(int i=0;i<trs.size();i++){
                Elements tds=trs.get(i).getElementsByTag("td");
                String nid=tds.get(0).text();
                String pid=tds.get(1).text();
                String title=tds.get(2).text();
                String date=tds.get(3).text();
                NoteItem noteItem=new NoteItem(nid,pid,title,date);
                noteItems.add(noteItem);
            }
            return noteItems;
        }
    }
}
