package thereisnospon.acclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import thereisnospon.acclient.event.Arg;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmp_activity_main);
        ButterKnife.bind(this);
        int id=getIntent().getIntExtra(Arg.LOAD_PROBLEM_DETAIL,1);

    }



}
