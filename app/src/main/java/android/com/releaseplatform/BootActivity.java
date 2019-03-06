package android.com.releaseplatform;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class BootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        if(Build.VERSION.SDK_INT >= 21)
        {
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ImageView imageView=findViewById(R.id.imageboot);
        /*动画效果*/
        AlphaAnimation a = new AlphaAnimation(0,1);
        a.setDuration(3000);//毫秒
        imageView.startAnimation(a);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(BootActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }

}
