package thereisnospon.acclient.base.fragment;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by yzr on 16/6/6.
 */


public abstract class WebViewFragment extends Fragment{

    WebView webView;

    public void loadLocalHtmlL(String data, String encoding){
        webView.loadDataWithBaseURL(null, data, "text/html", encoding, null);
    }

    public void initWebView(View container, @IdRes int webViewId){
        webView=(WebView)container.findViewById(webViewId);
    }

}
