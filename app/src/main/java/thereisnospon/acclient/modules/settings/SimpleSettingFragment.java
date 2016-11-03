package thereisnospon.acclient.modules.settings;

/**
 * Created by yzr on 16/8/3.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.Set;

import thereisnospon.acclient.R;

public  class SimpleSettingFragment extends PreferenceFragment
        implements
        Preference.OnPreferenceChangeListener,
        Preference.OnPreferenceClickListener{

    ListPreference theme;
    ListPreference compiler;
    Preference about;
    SwitchPreference exitConfirm;
    //CheckBoxPreference skin;
    Settings settings;


    private OnThemeChangeListener themeChangeListener;




    interface OnThemeChangeListener{
        void onThemeChange();
        void onFragmentCreate(Toolbar toolbar);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        addPreferencesFromResource(R.xml.settings);


        themeChangeListener=(OnThemeChangeListener)getActivity();

        settings=Settings.getInstance();
        theme=(ListPreference)findPreference(Settings.THEME_PREF);


        String themeSummary=theme.getEntries()[settings.getTheme()].toString();
        theme.setSummary(themeSummary);
        theme.setOnPreferenceChangeListener(this);
        exitConfirm=(SwitchPreference)findPreference(Settings.EXIT_CONFIRM);
        exitConfirm.setOnPreferenceChangeListener(this);

        /*about=findPreference(Settings.ABOUT_PREF);
        about.setOnPreferenceClickListener(this);*/

       // skin=(CheckBoxPreference) findPreference(Settings.SKIN_PREF);
       // skin.setOnPreferenceChangeListener(this);


        compiler=(ListPreference)findPreference(Settings.COMPILER);
        String compilerSummary=compiler.getEntries()[settings.getCompiler()].toString();
        compiler.setSummary(compilerSummary);
        compiler.setOnPreferenceChangeListener(this);
    }

    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=super.onCreateView(inflater, container, savedInstanceState);


        AppBarLayout appBarLayout=(AppBarLayout)inflater.inflate(R.layout.appbar,null,false);
        Toolbar toolbar=(Toolbar)appBarLayout.findViewById(R.id.toolbar);
        linearLayout=(LinearLayout)view;
        linearLayout.addView(appBarLayout,0);
        themeChangeListener.onFragmentCreate(toolbar);
        return view;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        listSummary(preference,newValue);
        if(preference==theme){
            settings.theme= Integer.parseInt(newValue.toString());
            settings.putString(Settings.THEME_PREF,settings.theme+"");
        }else if(preference==exitConfirm){
           settings.exitConfirm=Boolean.valueOf(newValue.toString());
           settings.putBoolean(Settings.EXIT_CONFIRM,settings.exitConfirm);
       }//else if(preference==skin){
           /*settings.skinPref=Boolean.valueOf(newValue.toString());
           settings.putBoolean(Settings.SKIN_PREF,settings.skinPref);
           themeChangeListener.onThemeChange();*/
      // }
        else if(preference==compiler){
           settings.compiler=Integer.parseInt(newValue.toString());
           settings.putString(Settings.COMPILER,settings.compiler+"");
       }
        return true;
    }

    public void listSummary(Preference preference,Object value){
        if(preference instanceof ListPreference){
            String str=getEntry((ListPreference)preference,value);
            preference.setSummary(str);
        }
    }

    String getEntry(ListPreference preference,Object newValue){
        int index=preference.findIndexOfValue(newValue.toString());
        CharSequence rets[]=preference.getEntries();
        return index>=0?rets[index].toString():null;
    }

    /**
     * 属性触摸时操作
     */
    @Override
    public boolean onPreferenceClick(Preference preference) {
        if(preference==about){
            Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onOptionsItemSelected(item)) {
                getActivity().onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}