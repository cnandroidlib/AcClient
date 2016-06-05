package thereisnospon.acclient.modules.problem_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import thereisnospon.acclient.R;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content,new HdojProblemFragment())
                .commit();
    }

}
