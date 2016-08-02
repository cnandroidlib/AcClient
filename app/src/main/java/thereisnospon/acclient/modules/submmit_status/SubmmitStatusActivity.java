package thereisnospon.acclient.modules.submmit_status;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;

public class SubmmitStatusActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submmit_status);
        SubmmitQuery query=new SubmmitQuery(null,SubmmitQuery.Status.AC);
        SubmmitFragment fragment=SubmmitFragment.newInstance(query);
        changeFragment(fragment);
    }
}
