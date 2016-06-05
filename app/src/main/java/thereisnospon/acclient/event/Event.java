package thereisnospon.acclient.event;

/**
 * Created by yzr on 16/6/5.
 */
public class Event<T> {

    private int eventCode;
    private T data;

    public Event(T data){
        this.data=data;
        this.eventCode=EventCode.EVENT_DEFAULT_CODE;
    }
    public Event(T data,int eventCode){
        this.data=data;
        this.eventCode=eventCode;
    }

    public T getData(){
        return  data;
    }
    public int getEventCode() {
        return eventCode;
    }

}
