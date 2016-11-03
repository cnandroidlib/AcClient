package thereisnospon.acclient.modules.about;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import java.io.FileReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;

public class AboutActivity extends AppCompatActivity {



    private static final String ABOUT="<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>关于</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<h1>关于</h1>\n" +
            "\t<pre>杭电oj客户端 AcClient 内部测试版。 不保证任何稳定性。。有</pre>\n" +
            "</body>\n" +
            "</html>";

    @BindView(R.id.webView) WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

webView.loadDataWithBaseURL(null,ABOUT,"text/html","utf-8",null);

    }
}
