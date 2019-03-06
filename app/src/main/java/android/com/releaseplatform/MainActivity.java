package android.com.releaseplatform;

import android.com.releaseplatform.FragmentFour.Fragment_Four;
import android.com.releaseplatform.FragmentOne.Fragment_One;
import android.com.releaseplatform.FragmentThree.Fragment_Three;
import android.com.releaseplatform.FragmentTwo.Fragment_Two;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    private Fragment_One mfragmentone;
    private Fragment_Two mfragmenttwo;
    private Fragment_Three mfragmentthree;
    private Fragment_Four mfragmentfour;
    private Fragment[] fragments;
    private int lastShowFragment = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (lastShowFragment != 1) {
                        switchFrament(lastShowFragment, 1);
                        lastShowFragment = 1;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (lastShowFragment != 2) {
                        switchFrament(lastShowFragment, 2);
                        lastShowFragment = 2;
                    }
                    return true;
                case R.id.navigation_llllllll:
                    if (lastShowFragment != 3) {
                        switchFrament(lastShowFragment, 3);
                        lastShowFragment = 3;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);//关闭切换动画
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
    }

    private void initFragments() {
        mfragmentone = new Fragment_One();
        mfragmenttwo = new Fragment_Two();
        mfragmentthree = new Fragment_Three();
        mfragmentfour = new Fragment_Four();

        fragments = new Fragment[]{mfragmentone, mfragmenttwo, mfragmentthree, mfragmentfour};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.framelayout, mfragmentone)
                .show(mfragmentone)
                .commit();
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.framelayout, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
    if(!mBackKeyPressed){
        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        mBackKeyPressed = true;
        new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
        @Override
        public void run() {
        mBackKeyPressed = false;//两秒后执行该段代码
         }
         },2000);
        }
    else{//退出程序
        this.finish();
        System.exit(0);
        }
    }
}
