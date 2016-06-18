package thereisnospon.acclient.base.activity;

import android.support.v4.app.Fragment;

import thereisnospon.acclient.R;

/**
 * Created by yzr on 16/6/18.
 */
public abstract class FragmentActivity extends DrawerActivity {

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content,fragment)
                .commit();
    }

}
