package thereisnospon.acclient.modules.settings;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit.http.PUT;
import thereisnospon.acclient.AppApplication;

/**
 * Created by yzr on 16/8/20.
 */
public class Settings {


    private static Settings instance;

    private static final String SETTING_FILE="settings_pref";


    public static final String SKIN_PREF="skins_pref";
    public static final String ABOUT_PREF="about_pref";
    public static final String THEME_PREF="theme_pref";
    public static final String EXIT_CONFIRM="exitcon_pref";
    public static final String COMPILER="compiler_pref";


    public int getTheme(){
        String str=getString(THEME_PREF,theme+"");
        int index=Integer.parseInt(str);
        return index;
    }

    public int getCompiler(){
        String str=getString(COMPILER,compiler+"");
        int index=Integer.parseInt(str);
        return index;
    }

    public int compiler=0;
    public int theme=0;
    public boolean exitConfirm=true;
    public boolean skinPref=false;


    private SharedPreferences mPreference;

    private Settings(Context context){

        mPreference=context.getSharedPreferences(SETTING_FILE,Context.MODE_PRIVATE);
    }

    public static Settings getInstance(){
        if(instance==null){
            instance=new Settings(AppApplication.context);
        }
        return instance;
    }


    public boolean ifExitConfirm(){
        return getBoolean(EXIT_CONFIRM,exitConfirm);
    }

    public boolean getBoolean(String key,boolean def){
        return mPreference.getBoolean(key,def);
    }

    public void putBoolean(String key,boolean value){
        mPreference.edit().putBoolean(key,value).commit();
    }

    public String getString(String key,String def){
        return mPreference.getString(key,def);
    }

    public int getInt(String key,int def){
        return mPreference.getInt(key,def);
    }

    public void putString(String key,String value){
        mPreference.edit().putString(key,value).commit();
    }

    public void putInt(String key,int value){
        mPreference.edit().putInt(key,value).commit();
    }
}
