package thereisnospon.acclient.modules.problem_list;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.SearchActivity;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojActivity extends SearchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        initDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content,new HdojProblemFragment())
                .commit();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("MMMENU",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}
