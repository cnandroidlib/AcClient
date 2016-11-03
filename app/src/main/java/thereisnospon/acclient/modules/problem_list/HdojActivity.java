package thereisnospon.acclient.modules.problem_list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.SearchActivity;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.event.Msg;
import thereisnospon.acclient.modules.problem_detail.ShowProblemActivity;
import thereisnospon.acclient.modules.problem_list.search_list.SearchProblemFragment;
import thereisnospon.acclient.modules.settings.Settings;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojActivity extends SearchActivity {

    public static final String TAG="HdojActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        initDrawer();
        changeFragment(new HdojProblemFragment());
        ButterKnife.bind(this);
        initView();
    }

    void initView(){

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Fragment fragment= SearchProblemFragment.newInstance(query);
        changeFragment(fragment);
        Logger.d(query);
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.problemlist_menu, menu);
        initSearch(menu);
        return true;
    }


    private void showPageDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=LayoutInflater.from(this).inflate(R.layout.alert_goto_page,null);
        final EditText editText=(EditText)view.findViewById(R.id.to_page);
        AlertDialog dialog=
                builder.setTitle("前往页数：")
                .setView(view)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToPage(editText.getText().toString());
                    }
                })
                .setNegativeButton("取消",null)
                .setCancelable(true)
                .create();
        dialog.show();
    }


    private void goToPage(String str){
        try{
            int page=Integer.parseInt(str);
            changeFragment(HdojProblemFragment.newInstance(page));
        }catch (NumberFormatException e){
            ViewGroup decor=(ViewGroup)getWindow().getDecorView();
            Toast.makeText(this,"页数不正确",Toast.LENGTH_SHORT).show();
        }
    }


    private void showIdDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=LayoutInflater.from(this).inflate(R.layout.alert_goto_page,null);
        final EditText editText=(EditText)view.findViewById(R.id.to_page);
        AlertDialog dialog=
                builder.setTitle("指定题号：")
                        .setView(view)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gotToId(editText.getText().toString());
                            }
                        })
                        .setNegativeButton("取消",null)
                        .setCancelable(true)
                        .create();
        dialog.show();
    }

    private void gotToId(String str){
        try{
            int id=Integer.parseInt(str);
            Intent intent=new Intent(this, ShowProblemActivity.class);
            intent.putExtra(Arg.LOAD_PROBLEM_DETAIL,id);
            startActivity(intent);
        }catch (NumberFormatException e){
            Toast.makeText(this,"id错误",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_problem_go_page:
               showPageDialog();
                break;
            case R.id.menu_problem_go_id:
                showIdDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    private boolean first=true;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(first&&Settings.getInstance().ifExitConfirm()){
                first=false;
                Snackbar.make(getDrawer(),"再按一次返回键回到桌面",Snackbar.LENGTH_SHORT)
                        .setAction("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
