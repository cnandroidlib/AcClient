package thereisnospon.acclient.modules.rank;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

import com.orhanobut.logger.Logger;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;
import thereisnospon.acclient.base.activity.SearchActivity;
import thereisnospon.acclient.modules.problem_list.search_list.SearchProblemFragment;
import thereisnospon.acclient.modules.search_people.SearchPeopleFragment;

/**
 * Created by yzr on 16/8/27.
 */
public class RankActivity extends SearchActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        Logger.d("create");
        initDrawer();
        changeFragment(new RankFragment());
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Fragment fragment= SearchPeopleFragment.newInstance(query);
        changeFragment(fragment);
        return true;
    }
}
