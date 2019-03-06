package android.com.releaseplatform;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TaobaoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        if(Build.VERSION.SDK_INT >= 21)
        {
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        WebView webView=(WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://re.taobao.com/search_ou?keyword=%E5%86%9C%E5%AE%B6%E8%8F%9C%E8%87%AA%E7%A7%8D&catid=&refpid=430292_1006&_input_charset=utf8&clk1=8a6d32368f8763197536f9b0e453ff47&spm=a231k.8165028.0782702701.6.44c92e63al0ULD");
    }
}
