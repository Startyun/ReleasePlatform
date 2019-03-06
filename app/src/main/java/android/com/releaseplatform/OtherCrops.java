package android.com.releaseplatform;

import android.com.releaseplatform.FragmentFour.Fragment_Four;
import android.com.releaseplatform.FragmentOne.Fragment_One;
import android.com.releaseplatform.FragmentThree.Fragment_Three;
import android.com.releaseplatform.FragmentTwo.Fragment_Two;
import android.com.releaseplatform.OtherCropsFragment.CropFragmentFive;
import android.com.releaseplatform.OtherCropsFragment.CropFragmentFour;
import android.com.releaseplatform.OtherCropsFragment.CropFragmentNull;
import android.com.releaseplatform.OtherCropsFragment.CropFragmentOne;
import android.com.releaseplatform.OtherCropsFragment.CropFragmentThree;
import android.com.releaseplatform.OtherCropsFragment.CropFragmentTwo;
import android.com.releaseplatform.RecyclerView.Bean;
import android.com.releaseplatform.RecyclerView.RecyclerBean;
import android.com.releaseplatform.RecyclerView.RecyclerBeanAdapter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OtherCrops extends AppCompatActivity {


    private RecyclerBeanAdapter recyclerBeanAdapter;
    private List<RecyclerBean>mlist=new ArrayList<>();
    private RecyclerView recyclerView;
    private CropFragmentOne mcropFragmentOne;
    private CropFragmentTwo mcropFragmentTwo;
    private CropFragmentThree mcropFragmentThree;
    private CropFragmentFour mcropFragmentFour;
    private CropFragmentFive mcropFragmentFive;
    private CropFragmentNull cropFragmentNull;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21)
        {
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_othercrops);
        recyclerView=(RecyclerView)findViewById(R.id.list);
        initrecycler();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerBeanAdapter=new RecyclerBeanAdapter(mlist);
        recyclerView.setAdapter(recyclerBeanAdapter);
        initfirstfragment();
        recyclerBeanAdapter.setOnItemClickListener(new RecyclerBeanAdapter.OnItemClickListener() {
            @Override
            public void onClick(int i) {
                if (i == 0) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.framelayout, mcropFragmentOne).commit();
                }
                if (i == 1) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (mcropFragmentTwo == null) {
                        mcropFragmentTwo = CropFragmentTwo.newInstance("稻谷类");
                    }
                    transaction.replace(R.id.framelayout, mcropFragmentTwo).commit();
                }
                if (i == 2) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (mcropFragmentThree == null) {
                        mcropFragmentThree = CropFragmentThree.newInstance("薯类");
                    }
                    transaction.replace(R.id.framelayout, mcropFragmentThree).commit();
                }
                if (i == 3) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (mcropFragmentFour == null) {
                        mcropFragmentFour = CropFragmentFour.newInstance("麦类");
                    }
                    transaction.replace(R.id.framelayout, mcropFragmentFour).commit();
                }
                if (i == 4) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (mcropFragmentFive == null) {
                        mcropFragmentFive = CropFragmentFive.newInstance("食用菌类");
                    }
                    transaction.replace(R.id.framelayout, mcropFragmentFive).commit();
                }
                if (i == 5) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (cropFragmentNull == null) {
                        cropFragmentNull = CropFragmentNull.newInstance("暂无");
                    }
                    transaction.replace(R.id.framelayout, cropFragmentNull).commit();
                }
                if (i == 6) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (cropFragmentNull == null) {
                        cropFragmentNull = CropFragmentNull.newInstance("暂无");
                    }
                    transaction.replace(R.id.framelayout, cropFragmentNull).commit();
                }
                if (i == 7) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (cropFragmentNull == null) {
                        cropFragmentNull = CropFragmentNull.newInstance("暂无");
                    }
                    transaction.replace(R.id.framelayout, cropFragmentNull).commit();
                }
                if (i == 8) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (cropFragmentNull == null) {
                        cropFragmentNull = CropFragmentNull.newInstance("暂无");
                    }
                    transaction.replace(R.id.framelayout, cropFragmentNull).commit();
                }
                if (i == 9) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (cropFragmentNull == null) {
                        cropFragmentNull = CropFragmentNull.newInstance("暂无");
                    }
                    transaction.replace(R.id.framelayout, cropFragmentNull).commit();
                }
                if (i == 10) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (cropFragmentNull == null) {
                        cropFragmentNull = CropFragmentNull.newInstance("暂无");
                    }
                    transaction.replace(R.id.framelayout, cropFragmentNull).commit();
                }
                if (i == 11) {
                    recyclerBeanAdapter.setPosition(i); //传递当前的点击位置
                    recyclerBeanAdapter.notifyDataSetChanged(); //通知刷新
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (cropFragmentNull == null) {
                        cropFragmentNull = CropFragmentNull.newInstance("暂无");
                    }
                    transaction.replace(R.id.framelayout, cropFragmentNull).commit();
                }

            }
        });

    }

    private void initrecycler(){
        RecyclerBean recyclerBean=new RecyclerBean("大豆类");
        mlist.add(recyclerBean);
        RecyclerBean recyclerBean1=new RecyclerBean("稻谷类");
        mlist.add(recyclerBean1);
        RecyclerBean recyclerBean2=new RecyclerBean("薯类");
        mlist.add(recyclerBean2);
        RecyclerBean recyclerBean3=new RecyclerBean("麦类");
        mlist.add(recyclerBean3);
        RecyclerBean recyclerBean4=new RecyclerBean("食用菌类");
        mlist.add(recyclerBean4);
        RecyclerBean recyclerBean5=new RecyclerBean("畜禽类");
        mlist.add(recyclerBean5);
        RecyclerBean recyclerBean6=new RecyclerBean("水产品类");
        mlist.add(recyclerBean6);
        RecyclerBean recyclerBean7=new RecyclerBean("粮油类");
        mlist.add(recyclerBean7);
        RecyclerBean recyclerBean8=new RecyclerBean("林产品类");
        mlist.add(recyclerBean8);
        RecyclerBean recyclerBean9=new RecyclerBean("茶叶类");
        mlist.add(recyclerBean9);
        RecyclerBean recyclerBean10=new RecyclerBean("中药材类");
        mlist.add(recyclerBean10);

    }




    /*默认选中第0个fragment*/
   private void initfirstfragment(){
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       if (mcropFragmentOne == null) {
           mcropFragmentOne = CropFragmentOne.newInstance("大豆类");
       }
       transaction.replace(R.id.framelayout, mcropFragmentOne).commit();
   }


}
