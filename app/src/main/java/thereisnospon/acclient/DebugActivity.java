package thereisnospon.acclient;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import thereisnospon.acclient.base.activity.DrawerActivity;
import thereisnospon.acclient.modules.login.LoginActivity;
import thereisnospon.acclient.modules.login.LoginUtil;
import thereisnospon.acclient.modules.problem_list.HdojActivity;
import thereisnospon.acclient.modules.search_people.SearchPeopleActivity;
import thereisnospon.acclient.modules.settings.SettingActivity;
import thereisnospon.acclient.modules.settings.Settings;
import thereisnospon.acclient.modules.show_code.CodeActivity;
import thereisnospon.acclient.modules.show_code.CodeFragment;
import thereisnospon.acclient.modules.submmit.SubmmitAnwserActivity;
import thereisnospon.acclient.modules.submmit_status.SubmmitStatusActivity;

public class DebugActivity extends DrawerActivity implements ListView.OnItemClickListener{



    ListView listView;
    ArrayAdapter<String>adapter;
    String []debugs=new String[]{
            "Login","problem","search","submmit","Code","Setting"
            ,"submmit anwser"
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class<?>cl=null;
        switch (position){
            case 0:cl= LoginActivity.class;
                break;
            case 1:cl= HdojActivity.class;
                break;
            case 2:cl= SearchPeopleActivity.class;
                break;
            case 3:cl= SubmmitStatusActivity.class;
                break;
            case 4:cl= CodeActivity.class;
                break;
            case 5:cl= SettingActivity.class;
                break;
            case 6:cl= SubmmitAnwserActivity.class;
                break;
            default:
                break;
        }
        Intent intent=new Intent(this,cl);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        initList();
        initDrawer();
        if(LoginUtil.cheackCookie()){
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"验证超时",Toast.LENGTH_SHORT).show();
        }
    }

    void initList(){
        listView=(ListView)findViewById(R.id.debug_list);
        List<String> list=new ArrayList<>();
        for(String s:debugs)
            list.add(s);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    boolean firstOnBack=true;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(Settings.getInstance().ifExitConfirm()&&firstOnBack){
                firstOnBack=false;
                Snackbar.make(listView,"再按一次返回键退出",Snackbar.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
