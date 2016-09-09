package thereisnospon.acclient.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import thereisnospon.acclient.R;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.about.AboutActivity;
import thereisnospon.acclient.modules.discuss.DiscussActivity;
import thereisnospon.acclient.modules.hello.HelloActivity;
import thereisnospon.acclient.modules.problem_list.HdojActivity;
import thereisnospon.acclient.modules.rank.RankActivity;
import thereisnospon.acclient.modules.rank.RankContact;
import thereisnospon.acclient.modules.settings.SettingActivity;
import thereisnospon.acclient.modules.user_detail.UserDetailActivity;
import thereisnospon.acclient.utils.SpUtil;

/**
 * Created by yzr on 16/6/6.
 */
public  abstract class DrawerActivity extends ThemeActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer!=null&&drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    Toolbar toolbar;
    DrawerLayout drawer;

    private String id;
    private String nickname;



    public void initDrawerWithToolBar(Toolbar toolbar){
        this.toolbar=toolbar;
        initDrawer();
    }

    public  void  initDrawer(){
        SpUtil util=SpUtil.getInstance();

        this.id=util.getString(SpUtil.NAME);
        this.nickname=util.getString(SpUtil.PASS);


        if(toolbar==null){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
        }

        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }

        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View  headerView=navigationView.getHeaderView(0);
        TextView textView=(TextView) headerView.findViewById(R.id.drawer_header_name);
        textView.setText(util.getString(SpUtil.NICKNAME));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent=null;
        switch (id)
        {
            case R.id.menu_contest:
                intent=new Intent(this, DiscussActivity.class);
                //Snackbar.make(drawer,"功能完善中...",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_info:
                intent=new Intent(this, UserDetailActivity.class);
                intent.putExtra(Arg.LOAD_USER_DETAIL, this.id);
                break;
            case R.id.menu_problem:
                intent=new Intent(this,HdojActivity.class);
                break;
            case R.id.menu_relogin:
                intent=new Intent(this, HelloActivity.class);
                intent.putExtra(Arg.RE_LOGIN,true);
                break;
            case R.id.menu_user:
                intent=new Intent(this, RankActivity.class);
                break;
            case R.id.menu_share:
                share();
                break;
            case R.id.menu_about:
                Intent intent1=new Intent(this, AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_setting:
                intent=new Intent(this, SettingActivity.class);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if(intent!=null){
            startActivity(intent);
        }
        return true;
    }



    private void share(){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享一个有用的APP");
        intent.putExtra(Intent.EXTRA_TEXT, "我发现了一个可以在手机上刷题(HduOj)的app,大家快来看看吧!");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }



}
