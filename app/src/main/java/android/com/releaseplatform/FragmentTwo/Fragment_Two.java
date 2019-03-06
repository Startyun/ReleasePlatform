package android.com.releaseplatform.FragmentTwo;

import android.com.releaseplatform.FragmentOne.Fragment_One;
import android.com.releaseplatform.OpinionActivity;
import android.com.releaseplatform.OtherCrops;
import android.com.releaseplatform.R;
import android.com.releaseplatform.SearchActivity;
import android.com.releaseplatform.TaobaoActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class Fragment_Two extends Fragment implements View.OnClickListener{

    private Banner banner;
    private TextView tet_search;
    private List<Integer>imageArray;
    private List<String>imageTitle;
    private ImageView ima_tuijian,ima_online,ima_xiaoxi,ima_qita,ima_taobao,ima_liuyanban;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment__two,container,false);

        tet_search=(TextView)view.findViewById(R.id.tet_search);
        banner=(Banner)view.findViewById(R.id.banner);
        ima_tuijian=(ImageView)view.findViewById(R.id.ima_tuijian);
        ima_online=(ImageView)view.findViewById(R.id.ima_online);
        ima_liuyanban=(ImageView)view.findViewById(R.id.ima_liuyanban);
        ima_taobao=(ImageView)view.findViewById(R.id.ima_taobao);
        ima_xiaoxi=(ImageView)view.findViewById(R.id.ima_xiaoxi);
        ima_qita=(ImageView)view.findViewById(R.id.ima_qita);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitBanner();
        ima_tuijian.setOnClickListener(this);
        ima_online.setOnClickListener(this);
        ima_taobao.setOnClickListener(this);
        tet_search.setOnClickListener(this);
        ima_liuyanban.setOnClickListener(this);
        ima_xiaoxi.setOnClickListener(this);
        ima_qita.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
        case R.id.ima_tuijian:
           Intent intent10 = new Intent(getActivity(), OtherCrops.class);
           startActivity(intent10);
           break;
        case R.id.ima_online:
            Intent intent12 = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:10086");
            intent12.setData(data);
            startActivity(intent12);
            break;
        case R.id.ima_liuyanban:
            Intent intent11 =new Intent(getActivity(), OpinionActivity.class);
            startActivity(intent11);
            break;
        case R.id.ima_taobao:
            Intent intent13=new Intent(getActivity(), TaobaoActivity.class);
            startActivity(intent13);
            break;
        case R.id.tet_search:
            Intent intent1=new Intent(getActivity(), SearchActivity.class);
            startActivity(intent1);
            break;
        case R.id.ima_xiaoxi:
           Toast.makeText(getActivity(),"功能待开发",Toast.LENGTH_SHORT).show();
            break;
        case R.id.ima_qita:
            Toast.makeText(getActivity(),"功能待开发",Toast.LENGTH_SHORT).show();
            break;
        default:
           break;
        }
    }

    public void InitBanner(){
        imageArray=new ArrayList<Integer>();
        imageArray.add(R.drawable.pic_crop1);
        imageArray.add(R.drawable.pic_crop2);
        imageArray.add(R.drawable.pic_crop3);
        imageArray.add(R.drawable.pic_crop4);

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
                Toast.makeText(getActivity(),"你点啥点！！！",Toast.LENGTH_SHORT).show();
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
