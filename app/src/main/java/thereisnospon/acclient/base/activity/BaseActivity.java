package thereisnospon.acclient.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import thereisnospon.acclient.R;
import thereisnospon.acclient.modules.settings.Settings;

/**
 * Created by yzr on 16/8/20.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void changeTheme(){
        /*Settings settings=Settings.getInstance();

            if(settings.getBoolean(Settings.SKIN_PREF,settings.skinPref)){
                setTheme(R.style.AppThemeNight);
                Logger.d("night");
            }else{
                setTheme(R.style.AppTheme);
                Logger.d("appTheme");
            }*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        changeTheme();
    }






}
