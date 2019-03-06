package android.com.releaseplatform.FragmentOne;

import android.app.Activity;
import android.com.releaseplatform.MyUri;
import android.com.releaseplatform.OkHttpUtil.HttpUtil;
import android.com.releaseplatform.OkHttpUtil.SendPost;
import android.com.releaseplatform.R;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hjq.xtoast.XToast;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


public class Fragment_One extends Fragment implements View.OnClickListener {



    public static  final int TAKE_File1=2;
    public static  final int TAKE_File2=3;
    public static  final int TAKE_File3=4;
    private boolean image=true;
    private boolean image1=true;
    private boolean image2=true;
    private boolean image3=true;
    private String myuri = "";
    private String myuri1 = "";
    private String myuri2 = "";
    private String myuri3 = "";




    final String urlPath = "http://114.115.245.160/linyunpeng/test";



    private ImageView ima_add1,ima_add2,ima_add3;
    private CircleImageView circleImageView;
    private Button btn_release;
    private EditText crop_username,crop_name,crop_price,crop_kucun,crop_yunfei,crop_fahuodi,crop_describe;
    private String result = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment__one,container,false);
        if(Build.VERSION.SDK_INT >= 21)
        {
            View decorView=getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ima_add1=(ImageView)view.findViewById(R.id.ima_add1);
        ima_add2=(ImageView)view.findViewById(R.id.ima_add2);
        ima_add3=(ImageView)view.findViewById(R.id.ima_add3);
        btn_release=(Button)view.findViewById(R.id.btn_release);
        circleImageView=(CircleImageView) view.findViewById(R.id.take_photo);
        crop_name=(EditText)view.findViewById(R.id.crop_name);
        crop_price=(EditText)view.findViewById(R.id.crop_price);
        crop_kucun=(EditText)view.findViewById(R.id.crop_kucun);
        crop_yunfei=(EditText)view.findViewById(R.id.crop_yunfei);
        crop_fahuodi=(EditText)view.findViewById(R.id.crop_fahuodi);
        crop_describe=(EditText)view.findViewById(R.id.crop_describe);
        crop_username=(EditText)view.findViewById(R.id.crop_username);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ima_add1.setOnClickListener(this);
        ima_add2.setOnClickListener(this);
        ima_add3.setOnClickListener(this);
        btn_release.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.ima_add1: /*Intent intent1=new Intent(Intent.ACTION_PICK);
                  intent1.setType("image/*");
                  startActivityForResult(intent1, TAKE_File1);*/
                  PictureSelector
                          .create(Fragment_One.this,1)
                          .selectPicture(true, 200, 200, 1, 1);
                  image1=false;
                  break;
              case R.id.ima_add2:
                  PictureSelector
                          .create(Fragment_One.this, 2)
                          .selectPicture(true, 200, 200, 1, 1);
                  image2=false;
                  break;
              case R.id.ima_add3:
                  PictureSelector
                          .create(Fragment_One.this, 3)
                          .selectPicture(true, 200, 200, 1, 1);
                  image3=false;
                  break;
              case R.id.btn_release:
                  /*
                  与 "".equals(crop_name.getText().toString())效果相同
                  * */
                  if(crop_name.getText().toString().length()==0||
                          crop_price.getText().toString().length()==0||
                          crop_kucun.getText().toString().length()==0||
                          crop_yunfei.getText().toString().length()==0||
                          crop_fahuodi.getText().toString().length()==0||
                          crop_username.getText().toString().length()==0||
                          crop_describe.getText().toString().length()==0||image||image2||image3||image1){
                      new XToast(getActivity())
                              .setDuration(2000)
                              .setView(R.layout.toast_hint)
                              .setAnimStyle(android.R.style.Animation_Translucent)
                              .setImageDrawable(android.R.id.icon, R.mipmap.ic_dialog_tip_error)
                              .setText(android.R.id.message, "请完善所有信息")
                              .show();
                  }else{
                      if(!JudgePoint()){
                          break;
                      }
                      SendPostData();
                      new XToast(getActivity())
                              .setDuration(2000)
                              .setView(R.layout.toast_hint)
                              .setAnimStyle(android.R.style.Animation_Translucent)
                              .setImageDrawable(android.R.id.icon, R.mipmap.ic_dialog_tip_finish)
                              .setText(android.R.id.message, "发布成功请等待")
                              .show();
                      emptymessage();
                  }
                  break;
              case R.id.take_photo:
                  PictureSelector
                          .create(Fragment_One.this, PictureSelector.SELECT_REQUEST_CODE)
                          .selectPicture(true, 200, 200, 1, 1);
                  image=false;
                  break;
              default:
              break;
          }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            Toast.makeText(getActivity(),"请选择图片",Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode){
            case 1:
               /* Bitmap bitmap1 = null;
                Toast.makeText(getActivity(),"已选择图片",Toast.LENGTH_SHORT).show();
                try {
                     bitmap1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ima_add1.setImageBitmap(bitmap1);
                MyUri.myuri1=bitmapToString(bitmap1);*/
                //ima_add1.setImageURI(data.getData());
                String picturePath1 = data.getStringExtra(PictureSelector.PICTURE_PATH);
                ima_add1.setImageBitmap(ImageUtils.getBitmap(picturePath1));
                myuri1=bitmapToString(ImageUtils.getBitmap(picturePath1));
                break;
            case 2:
                String picturePath2 = data.getStringExtra(PictureSelector.PICTURE_PATH);
                ima_add2.setImageBitmap(ImageUtils.getBitmap(picturePath2));
                myuri2=bitmapToString(ImageUtils.getBitmap(picturePath2));
                break;
            case 3:
                String picturePath3 = data.getStringExtra(PictureSelector.PICTURE_PATH);
                ima_add3.setImageBitmap(ImageUtils.getBitmap(picturePath3));
                myuri3=bitmapToString(ImageUtils.getBitmap(picturePath3));
                break;
            case PictureSelector.SELECT_REQUEST_CODE:
                    String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                    circleImageView.setImageBitmap(ImageUtils.getBitmap(picturePath));
                    myuri=bitmapToString(ImageUtils.getBitmap(picturePath));
                break;
                   default:
                       break;
           }
    }




    private void SendPostData(){
        HttpUtil.sendOkHttpPost(urlPath, PrepareData(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                 result=SendPost.sendPost(urlPath,PrepareData());
                Log.i(TAG,"返回结果"+result);
            }
        }).start();
*/
    }


    private String PrepareData() {
        String content=null;
        try {
            JSONObject object1 = new JSONObject();
            object1.put("cropName", crop_name.getText().toString());
            object1.put("cropPrice", crop_price.getText().toString());
            object1.put("cropKucun", crop_kucun.getText().toString());
            object1.put("cropYunfei", crop_yunfei.getText().toString());
            object1.put("cropFahuodi", crop_fahuodi.getText().toString());
            object1.put("cropUsername", crop_username.getText().toString());
            object1.put("cropDescribe", crop_describe.getText().toString());
            object1.put("imaAdd1", myuri1);
            object1.put("imaAdd2", myuri2);
            object1.put("imaAdd3", myuri3);
            object1.put("imaTouxiang", myuri);
            content = String.valueOf(object1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return content;

    }



    private void emptymessage(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                crop_name.setText(null);
               /* 效果一样
               crop_name.setText("");*/
                crop_price.setText(null);
                crop_kucun.setText(null);
                crop_yunfei.setText(null);
                crop_fahuodi.setText(null);
                crop_username.setText(null);
                crop_describe.setText(null);
                ima_add1.setImageResource(R.mipmap.pic_add);
                ima_add2.setImageResource(R.mipmap.pic_add);
                ima_add3.setImageResource(R.mipmap.pic_add);
                circleImageView.setImageResource(R.mipmap.pic_user1);
                image=true;
                image1=true;
                image2=true;
                image3=true;

            }
        });
    }


    private boolean JudgePoint(){
        Pattern shuzi = Pattern.compile("[0-9]*");
        Pattern hanzi=Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = shuzi.matcher(crop_price.getText().toString());
        Matcher m1 = shuzi.matcher(crop_kucun.getText().toString());
        Matcher m2= shuzi.matcher(crop_yunfei.getText().toString());
        String str=crop_fahuodi.getText().toString();

        if(!m.matches()){
            Toast.makeText(getActivity(),"请输入正确的价格",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!m1.matches()){
            Toast.makeText(getActivity(),"请输入正确的库存",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!m2.matches()){
            Toast.makeText(getActivity(),"请输入正确的运费",Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i=0;i<str.length();i++){
            Matcher m3= hanzi.matcher(String.valueOf(str.charAt(i)));
            if(!m3.matches()){
                Toast.makeText(getActivity(),"请输入正确的发货地",Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;

    }


    /*把bitmap转换成string*/
    public static String bitmapToString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream btString = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, btString);//压缩图片
        byte[] bytes = btString.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }




}
