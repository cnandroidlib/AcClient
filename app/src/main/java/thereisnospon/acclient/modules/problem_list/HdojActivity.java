package thereisnospon.acclient.modules.problem_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.SearchActivity;
import thereisnospon.acclient.event.Msg;
import thereisnospon.acclient.modules.problem_list.search_list.SearchProblemFragment;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojActivity extends SearchActivity {

    public static final String TAG="HdojActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        initDrawer();
        changeFragment(new HdojProblemFragment());
        ButterKnife.bind(this);
        initView();
    }

    void initView(){

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Fragment fragment= SearchProblemFragment.newInstance(query);
        changeFragment(fragment);
        Logger.d(query);
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.problemlist_menu, menu);
        initSearch(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_problem_go_page:
                Msg.t("go page");
                break;
            case R.id.menu_problem_go_id:
                Msg.t("go id");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
