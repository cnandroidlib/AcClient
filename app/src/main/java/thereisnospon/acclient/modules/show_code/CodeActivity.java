package thereisnospon.acclient.modules.show_code;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.FragmentActivity;
import thereisnospon.acclient.event.Arg;

public class CodeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Intent intent=getIntent();
        String id=intent.getStringExtra(Arg.SHOWCODE_ID);
        String code=intent.getStringExtra(Arg.SHOWCODE_CODE);
        Fragment fragment=null;
        if(code!=null){
            fragment=CodeFragment.newCodeInstance(code);
        }else if(id !=null){
            fragment=CodeFragment.newCodeInstance(id);
        }else{
            fragment=CodeFragment.newInstance("17562992");
        }
        changeFragment(fragment);
    }
}
