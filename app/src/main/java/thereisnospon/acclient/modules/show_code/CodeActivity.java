package thereisnospon.acclient.modules.show_code;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;

public class CodeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Fragment fragment=CodeFragment.newInstance("17562992");
        changeFragment(fragment);
    }
}
