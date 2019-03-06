package android.com.releaseplatform.StorageCrop;

import android.com.releaseplatform.GoodsActivity;
import android.com.releaseplatform.R;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeidouActivity extends AppCompatActivity {

    private TextView tet_name,tet_price,tet_kucun,tet_yunfei,tet_fahuodi,tet_describe,tet_username;
    private ImageView ima,ima1,ima2;
    private Banner banner;
    private List<Integer> imageArray;
    private List<String>imageTitle;
    private CircleImageView circleImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        tet_name=(TextView)findViewById(R.id.tet_name);
        tet_price=(TextView)findViewById(R.id.tet_price);
        tet_kucun=(TextView)findViewById(R.id.tet_kucun);
        tet_yunfei=(TextView)findViewById(R.id.tet_yunfei);
        tet_fahuodi=(TextView)findViewById(R.id.tet_fahuodi);
        tet_describe=(TextView)findViewById(R.id.tet_describe);
        tet_username=(TextView)findViewById(R.id.tet_username);
        circleImageView=(CircleImageView)findViewById(R.id.circleImageView);
        ima=(ImageView)findViewById(R.id.ima);
        ima1=(ImageView)findViewById(R.id.ima1);
        ima2=(ImageView)findViewById(R.id.ima2);
        banner=(Banner)findViewById(R.id.banner);
        tet_name.setText("农场品名： 黑豆");
        tet_price.setText("价格:  8.9"+"元");
        tet_kucun.setText("库存:  66");
        tet_yunfei.setText("运费:  10"+"元");
        tet_fahuodi.setText("发货地:  南宁");
        tet_describe.setText("产品描述:  黑豆为豆科植物大豆（学名：Glycinemax（L.）merr）的黑色种子。黑豆蛋白质含量36%，易于消化，对满足人体对蛋白质的需要具有重要意义; 脂肪含量16%，主要含不饱和脂肪酸，吸收率高达95%，除满足人体对脂肪的需要外，还有降低血液中胆固醇的作用");
        tet_username.setText("黑豆专卖");
        imageArray=new ArrayList<Integer>();
        imageArray.add(R.drawable.pic_search1);
        imageArray.add(R.drawable.pic_search11);
        imageArray.add(R.drawable.pic_search111);
        Glide.with(HeidouActivity.this).load(R.drawable.pic_search1).into(ima);
        Glide.with(HeidouActivity.this).load(R.drawable.pic_search11).into(ima1);
        Glide.with(HeidouActivity.this).load(R.drawable.pic_search111).into(ima2);
        Glide.with(HeidouActivity.this).load(R.drawable.pic_touxiang).into(circleImageView);
        InitBanner();
    }


    public void InitBanner(){


        //设置图片标题集合
        imageTitle = new ArrayList<>();
        imageTitle.add("剑魔");
        imageTitle.add("剑魔lol");
        imageTitle.add("剑魔LOL");
        imageTitle.add("剑魔LOL");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new MyLoader11());
        //设置图片集合
        banner.setImages(imageArray);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.CubeOut);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(imageTitle);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(HeidouActivity.this,"你点啥点！！！",Toast.LENGTH_SHORT).show();
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class MyLoader11 extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


}
