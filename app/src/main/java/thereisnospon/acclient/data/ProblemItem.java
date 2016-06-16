package thereisnospon.acclient.data;

/**
 * Created by yzr on 16/6/10.
 */


public class ProblemItem {

    int id;
    String title;
    int accepted;
    int submmision;

    public ProblemItem(int id, String title, int accepted, int submmision) {
        this.id = id;
        this.title = title;
        this.accepted = accepted;
        this.submmision = submmision;
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

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getSubmmision() {
        return submmision;
    }

    public void setSubmmision(int submmision) {
        this.submmision = submmision;
    }

}
