package thereisnospon.acclient.modules.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.modules.discuss.DiscussActivity;

public class NoteActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        initDrawer();
        changeFragment(new NoteFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_nosearch,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      return super.onOptionsItemSelected(item);
    }
}
