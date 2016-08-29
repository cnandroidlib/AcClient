package thereisnospon.acclient.modules.user_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;
import thereisnospon.acclient.event.Arg;

public class UserDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        String id=getIntent().getStringExtra(Arg.LOAD_USER_DETAIL);
        UserDetailFragment fragment=UserDetailFragment.newInstance(id);
        initDrawer();
        changeFragment(fragment);
    }
}
