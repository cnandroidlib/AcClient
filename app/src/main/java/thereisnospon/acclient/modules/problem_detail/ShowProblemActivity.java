package thereisnospon.acclient.modules.problem_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.event.Arg;

public class ShowProblemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_problem);
        ButterKnife.bind(this);
        int id=getIntent().getIntExtra(Arg.LOAD_PROBLEM_DETAIL,1000);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content,new ShowProblemFragment())
                .commit();
    }
}
