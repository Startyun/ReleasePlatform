package android.com.releaseplatform.FragmentFour;

import android.app.ProgressDialog;
import android.com.releaseplatform.R;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Fragment_Four extends Fragment {

    private int min=10;
    private int max=99;
    private ListView list;
    private String []iconname={"意见反馈","隐私权限","关于软件"};
    private int[]icon={R.mipmap.pic_about,R.mipmap.pic_opinion,R.mipmap.pic_privacy};
    private List<Map<String,Object>>datalist=new ArrayList<Map<String,Object>>();
    private SimpleAdapter simpleAdapter;
    private TextView tet1,tet2,tet3,tet4,tet5,tet6,tet7,tet8;
    private ImageView ima1;
    public ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment__four,container,false);
        if(Build.VERSION.SDK_INT >= 21)
        {
            View decorView=getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        list=(ListView)view.findViewById(R.id.fragment4_list);
       simpleAdapter=new SimpleAdapter(getActivity(),getData(),R.layout.fragmentfour_item,
                new String[]{"image","text"},new int[]{R.id.fragment4_pic,R.id.fragment4_tet});
        list.setAdapter(simpleAdapter);
        tet1=(TextView)view.findViewById(R.id.tet1);
        tet2=(TextView)view.findViewById(R.id.tet2);
        tet3=(TextView)view.findViewById(R.id.tet3);
        tet4=(TextView)view.findViewById(R.id.tet4);
        tet5=(TextView)view.findViewById(R.id.tet5);
        tet6=(TextView)view.findViewById(R.id.tet6);
        tet7=(TextView)view.findViewById(R.id.tet7);
        tet8=(TextView)view.findViewById(R.id.tet8);
        ima1=(ImageView)view.findViewById(R.id.ima1);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ima1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initpoint();
            }
        });
    }

    private List<Map<String,Object>> getData() {

        for(int i=0;i<icon.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",icon[i]);
            map.put("text",iconname[i]);
            datalist.add(map);
        }
        return  datalist;
    }



    private void initpoint(){
        Random random = new Random();
        final int num1 = random.nextInt(max)%(max-min+1) + min;
        final int num2 = random.nextInt(max)%(max-min+1) + min;
        final int num3 = random.nextInt(max)%(max-min+1) + min;
        final int num4 = random.nextInt(max)%(max-min+1) + min;
        final int num5 = random.nextInt(max)%(max-min+1) + min;
        final int num6 = random.nextInt(max)%(max-min+1) + min;
        final int num7 = random.nextInt(max)%(max-min+1) + min;
        final int num8 = random.nextInt(max)%(max-min+1) + min;
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tet1.setText(num1+"");
                        tet2.setText(num2+"");
                        tet3.setText(num3+"");
                        tet4.setText(num4+"");
                        tet5.setText(num5+"");
                        tet6.setText(num6+"");
                        tet7.setText(num7+"");
                        tet8.setText(num8+"");
                        closeProgressDialog();
                    }
                });

            }
        }).start();
    }

    /*
     * 显示进度对话框*/
    private void showProgressDialog() {
        if(progressDialog ==null) {
            progressDialog =new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }




    /*
     * 关闭进度对话框*/
    private void closeProgressDialog() {
        if(progressDialog !=null) {
            progressDialog.dismiss();
        }
    }


}