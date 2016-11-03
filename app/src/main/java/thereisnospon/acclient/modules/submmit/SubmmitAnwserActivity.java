package thereisnospon.acclient.modules.submmit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.BaseActivity;
import thereisnospon.acclient.data.SubmmitStatus;
import thereisnospon.acclient.event.Arg;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.Msg;
import thereisnospon.acclient.modules.settings.Settings;
import thereisnospon.acclient.modules.show_code.CodeActivity;
import thereisnospon.acclient.modules.submmit_status.SubmmitQuery;
import thereisnospon.acclient.modules.submmit_status.SubmmitStatusActivity;
import thereisnospon.acclient.utils.SpUtil;
import thereisnospon.acclient.utils.StringCall;

public class SubmmitAnwserActivity extends BaseActivity implements View.OnClickListener {




    private String problemId="1000";

    private EditText submmitcode;
    private Toolbar toolbar;
    private Spinner spinner;

    private LinearLayout bottomsheet;

    private FloatingActionButton submmitfab;
    private TextView submmitReview;
    private TextView submmitSubmmit;
    BottomSheetBehavior behavior;

    int compilerChoose = 0;

    void submmit(){
        Msg.t("submmit");
        $submmit();
    }

    void review(){
        Msg.t("review");
        Intent intent=new Intent(this, CodeActivity.class);
        String code=submmitcode.getText().toString()+"";
        intent.putExtra(Arg.SHOWCODE_CODE,code);
        startActivity(intent);
    }

    void submmitSuccess(String html){
        List<SubmmitStatus>list=SubmmitStatus.Builder.parse(html);
        Toast.makeText(this,"submmit success"+list.size(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, SubmmitStatusActivity.class);
        intent.putExtra(Arg.SUBMMIT_QUERY_USER, SpUtil.getInstance().getString(SpUtil.NAME));
        intent.putExtra(Arg.SUBMMIT_QUERY_STATUS, SubmmitQuery.Status.ALL.getValue());
        startActivity(intent);
    }

    void $submmit(){
        final String code=submmitcode.getText().toString();
        final String lan= compilerChoose +"";
        SubmmitUtil.submmit(problemId, lan, code, new StringCall() {
            @Override
            public void success(String nickName) {
                submmitSuccess(nickName);
            }
            @Override
            public void failure(String msg) {
                Msg.t("提交失败");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submmit_anwser);
        ButterKnife.bind(this);
        initView();
        resolveId();
    }

    void resolveId(){
        Intent intent=getIntent();
        problemId=intent.getStringExtra(Arg.SBUMMIT_PROBLEM_ID);
        setTitle(problemId);
    }

    void initView(){
        findView();
        setSupportActionBar(toolbar);
        initBottmsheet();
        //submmitcode.setText(SubmmitUtil.CODE );
        initSpinner();
        setTitle(problemId);
    }

    void findView(){
        this.bottomsheet = (LinearLayout) findViewById(R.id.bottomsheet);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.submmitfab = (FloatingActionButton) findViewById(R.id.submmit_fab);
        this.submmitcode = (EditText) findViewById(R.id.submmit_code);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.submmitReview=(TextView)findViewById(R.id.submmit_review);
        this.submmitSubmmit=(TextView)findViewById(R.id.submmit_submmit);
    }


    void initBottmsheet() {

        behavior=BottomSheetBehavior.from(bottomsheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        submmitcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        submmitfab.setOnClickListener(this);
        submmitReview.setOnClickListener(this);
        submmitSubmmit.setOnClickListener(this);
        submmitcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submmit_fab:
                if(behavior.getState()==BottomSheetBehavior.STATE_EXPANDED)
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                else behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.submmit_review:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                review();
                break;
            case R.id.submmit_submmit:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                submmit();
                break;
        }
    }


    private void initSpinner() {

        int defualtComplier=Settings.getInstance().getCompiler();

        String[] mItems = getResources().getStringArray(R.array.code_languages);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(defualtComplier);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
               onComplierChange(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void onComplierChange(int position) {
        compilerChoose = position;
        String[] codeLanguages = getResources().getStringArray(R.array.code_languages);
        Toast.makeText(this, codeLanguages[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_submmit_code:
                Msg.t("menu");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submmit_menu, menu);
        return true;
    }

}
