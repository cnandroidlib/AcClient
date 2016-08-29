package thereisnospon.acclient.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import thereisnospon.acclient.R;
import thereisnospon.acclient.modules.settings.Settings;

/**
 * Created by yzr on 16/8/29.
 */
public class ThemeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();

    }


    void initTheme(){
        Settings settings = Settings.getInstance();
        if (settings.getBoolean(Settings.SKIN_PREF, settings.skinPref)) {
            setTheme(R.style.AppThemeNightNoactionBar);
        } else {
            setTheme(R.style.AppThemeNoActionBar);
            Logger.d("appTheme");
        }

    }
}
