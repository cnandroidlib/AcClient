package thereisnospon.acclient.modules.submmit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.BaseActivity;
import thereisnospon.acclient.data.SubmmitStatus;
import thereisnospon.acclient.event.Event;
import thereisnospon.acclient.event.EventCode;
import thereisnospon.acclient.modules.login.LoginUtil;
import thereisnospon.acclient.modules.settings.Settings;

public class SubmmitAnwserActivity extends BaseActivity {

    private Spinner spinner;

    int language = 0;
    private EditText submmitcode;
    private EditText loginusername;
    private EditText loginpassword;
    private Button loginbutton;

    @OnClick(R.id.login_button)
    public void onClick() {
        String userName=loginusername.getText().toString();
        String password=loginpassword.getText().toString();
        LoginUtil.login(userName,password);
    }

    void onLoginSuccess(Event<String> event){
        Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
        final String code=submmitcode.getText().toString();
        final String problemId="1000";
        final String lan=language+"";
        SubmmitUtil.submmit(problemId, lan, code
                , new Action1<String>() {
                    @Override
                    public void call(String s) {
                            submmitSuccess(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.d(throwable.getMessage());
                    }
                });
    }

    void submmitSuccess(String html){
        List<SubmmitStatus>list=SubmmitStatus.Builder.parse(html);
        Logger.d(list.size());
        Toast.makeText(this,"submmit success"+list.size(),Toast.LENGTH_SHORT).show();
    }

    void onLoginFailure(Event<String> event){

    }


    @Subscribe
    public void onNewEvent(Event<String> event) {
        switch (event.getEventCode()) {
            case EventCode.LOGIN_SUCCESS:
                onLoginSuccess(event);
                break;
            case EventCode.LOGIN_FAILURE:
                onLoginFailure(event);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submmit_anwser);
        ButterKnife.bind(this);
        this.loginbutton = (Button) findViewById(R.id.login_button);
        this.loginpassword = (EditText) findViewById(R.id.login_password);
        this.loginusername = (EditText) findViewById(R.id.login_username);
        this.submmitcode = (EditText) findViewById(R.id.submmit_code);
        this.spinner = (Spinner) findViewById(R.id.spinner);


        submmitcode.setText(SubmmitUtil.CODE );
        initSpinner();

        EventBus.getDefault().register(this);
    }

    private void initSpinner() {

        int defualtComplier=Settings.getInstance().getCompiler();


        this.spinner = (Spinner) findViewById(R.id.spinner);

        String[] mItems = getResources().getStringArray(R.array.code_languages);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(defualtComplier);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                onSelect(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }


    public void onSelect(int position) {
        language = position;
        String[] codeLanguages = getResources().getStringArray(R.array.code_languages);
        Toast.makeText(this, codeLanguages[position], Toast.LENGTH_SHORT).show();
    }


}
