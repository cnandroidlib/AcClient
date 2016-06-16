package thereisnospon.acclient.modules.problem_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.EventCode;

public class ShowProblemActivity extends AppCompatActivity {

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_problem);
        ButterKnife.bind(this);
        id=getIntent().getIntExtra(Arg.LOAD_PROBLEM_DETAIL,1000);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content,ShowProblemFragment.newInstance(id))
                .commit();
    }
}
