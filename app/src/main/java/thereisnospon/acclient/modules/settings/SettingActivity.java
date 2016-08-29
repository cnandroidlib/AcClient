package thereisnospon.acclient.modules.settings;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.DrawerActivity;

public class SettingActivity extends DrawerActivity
        implements SimpleSettingFragment.OnThemeChangeListener{


    private ImageView settingimage;
    private ViewGroup settingcontent;
    private SimpleSettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(R.layout.activity_setting);
        initDrawer();
        initView();
        addFragment();

    }

    void changeTheme(){
        setDrawableCache();
        initTheme();
        getState();
    }


    @Override
    public void onFragmentCreate(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        initDrawerWithToolBar(toolbar);
        startAnimation(settingimage);

    }

    void getState(){
        addFragment();
    }

    void addFragment(){
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(settingFragment!=null){
            transaction.remove(settingFragment);
        }
        settingFragment=new SimpleSettingFragment();
        transaction.add(R.id.fragment_content,settingFragment);
        transaction.commit();
    }


    private Bitmap bitmap;


    //TODO
    //可能OOM
    void setDrawableCache(){
        settingcontent.setDrawingCacheEnabled(false);
        settingcontent.setDrawingCacheEnabled(true);
        bitmap=Bitmap.createBitmap(settingcontent.getDrawingCache());
        //saveBitmap(bitmap);
        settingimage.setImageBitmap(bitmap);
        settingimage.setAlpha(1f);
        settingimage.setVisibility(View.VISIBLE);
    }
    /*void saveBitmap(Bitmap bitmap){
        File file=getCacheDir();
        File x=new File(file,"233.png");
        try(FileOutputStream out=new FileOutputStream(x)){
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.flush();
            Logger.d("save ok"+x.getAbsolutePath());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/

    void startAnimation(final View view){


        ValueAnimator animator=ValueAnimator.ofFloat(1f).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float n = (float) animation.getAnimatedValue();
                view.setAlpha(1f - n);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(bitmap!=null&&!bitmap.isRecycled()){
                    bitmap.recycle();
                    bitmap=null;
                }
                settingimage.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
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

    void initView(){
        this.settingcontent = (ViewGroup) findViewById(R.id.setting_content);
        this.settingimage = (ImageView) findViewById(R.id.setting_image);

    }

    @Override
    public void onThemeChange() {
        changeTheme();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_nosearch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return false;
    }
}
