package thereisnospon.acclient.modules.submmit_status;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;
import thereisnospon.acclient.data.SubmmitStatus;
import thereisnospon.acclient.event.Arg;

public class SubmmitStatusActivity extends FragmentActivity {


    private SubmmitQuery parseQuery(){
        Intent intent=getIntent();
        String user=intent.getStringExtra(Arg.SUBMMIT_QUERY_USER);
        String status=intent.getStringExtra(Arg.SUBMMIT_QUERY_STATUS);
        SubmmitQuery.Status s= SubmmitQuery.Status.ALL;
        if(SubmmitQuery.Status.AC.getValue().equals(status)){
            s=SubmmitQuery.Status.AC;
        }
        return new SubmmitQuery(user,s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submmit_status);
        initDrawer();
        SubmmitQuery query=parseQuery();
        SubmmitFragment fragment=SubmmitFragment.newInstance(query);
        changeFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_nosearch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}
