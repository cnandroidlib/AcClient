package thereisnospon.acclient.modules.discuss;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.DrawerActivity;
import thereisnospon.acclient.base.activity.FragmentActivity;
import thereisnospon.acclient.event.Arg;

/**
 * Created by yzr on 16/9/9.
 */
public class DiscussActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        initDrawer();
        String pid=getIntent().getStringExtra(Arg.PROBLEM_DISUCSS);
        if(pid==null){
            changeFragment(DiscussFragment.newIndexDiscuss());
        }else{
            changeFragment(DiscussFragment.newProbleDisuss(pid));
        }
    }
}
