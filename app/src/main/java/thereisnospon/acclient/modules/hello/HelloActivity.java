package thereisnospon.acclient.modules.hello;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import thereisnospon.acclient.R;

public class HelloActivity extends AppCompatActivity {


    private android.widget.ImageView hellologo;
    private android.widget.LinearLayout loginlinear;
    private android.widget.TextView hellotitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        this.hellotitle = (TextView) findViewById(R.id.hello_title);

        this.loginlinear = (LinearLayout) findViewById(R.id.login_linear);
        this.hellologo = (ImageView) findViewById(R.id.hello_logo);
        final ValueAnimator animator=ValueAnimator.ofFloat(1.0f,0.6f);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.setDuration(1000);

        hellologo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginlinear.setVisibility(View.VISIBLE);
               LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)hellotitle.getLayoutParams();
                params.bottomMargin=0;
                params.topMargin=0;
                hellotitle.invalidate();
                Logger.d(((LinearLayout.LayoutParams) hellotitle.getLayoutParams()).bottomMargin);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        hellologo.setScaleX((Float) animation.getAnimatedValue());
                        hellologo.setScaleY((Float)animation.getAnimatedValue());
                    }
                });
                animator.start();
            }
        });
    }


}
