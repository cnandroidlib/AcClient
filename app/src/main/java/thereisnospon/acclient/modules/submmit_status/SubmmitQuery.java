package thereisnospon.acclient.modules.submmit_status;

import thereisnospon.acclient.data.SubmmitStatus;

/**
 * Created by yzr on 16/7/30.
 */
public class SubmmitQuery {

    private String user;
    private Status status;

    public SubmmitQuery(String user,Status status){
        this.user=user;
        this.status=status;
    }


    @Override
    public String toString() {
        return query();
    }

    public String query(){
        StringBuilder builder=new StringBuilder();
        if(user!=null)
            builder.append("use="+user);
        if(status!=null)
            builder.append("status="+status.value);
        return builder.toString();
    }

    public enum Status{
        AC("accept"),ALL("#status");
        private String value;
        public String getValue(){
            return value;
        }
        Status(String val){
            value=val;
        }
    }


}

