package thereisnospon.acclient.modules.rank;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.orhanobut.logger.Logger;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;

/**
 * Created by yzr on 16/8/27.
 */
public class RankActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        Logger.d("create");
        changeFragment(new RankFragment());
    }


}
