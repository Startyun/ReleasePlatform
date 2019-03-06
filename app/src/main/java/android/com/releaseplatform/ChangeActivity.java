package android.com.releaseplatform;

import android.com.releaseplatform.FragmentOne.Fragment_One;
import android.com.releaseplatform.OkHttpUtil.SendPost;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjq.xtoast.XToast;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edit_cropname,edit_cropPrice,edit_cropKucun,edit_cropYunfei,edit_cropFahuodi,edit_cropUsername,edit_cropDescribe;
    private CircleImageView circleImageView,changecircleImageView;
    private ImageView imaadd1,imaadd2,imaadd3,changeimaadd1,changeimaadd2,changeimaadd3;
    private Button btn_touxiang,btn_imaadd1,btn_imaadd2,btn_imadd3,btn_change;
    private String result = "";
    private String cropchangeName="";
    final String urlPath = "http://114.115.245.160/linyunpeng/update";
    private boolean ima1=true;
    private boolean ima2=true;
    private boolean ima3=true;
    private boolean ima=true;
    private String imageview1,imageview2,imageview3,imageview;
    private String changemyuri="";
    private String changemyuri1="";
    private String changemyuri2="";
    private String changemyuri3="";
    private boolean lag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        if(Build.VERSION.SDK_INT >= 21)
        {
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        edit_cropname=(EditText)findViewById(R.id.edit_cropname);
        edit_cropPrice=(EditText)findViewById(R.id.edit_cropPrice);
        edit_cropKucun=(EditText)findViewById(R.id.edit_cropKucun);
        edit_cropYunfei=(EditText)findViewById(R.id.edit_cropYunfei);
        edit_cropFahuodi=(EditText)findViewById(R.id.edit_cropFahuodi);
        edit_cropUsername=(EditText)findViewById(R.id.edit_cropUsername);
        edit_cropDescribe=(EditText)findViewById(R.id.edit_cropDescribe);
        circleImageView=(CircleImageView)findViewById(R.id.circleImageView);
        changecircleImageView=(CircleImageView)findViewById(R.id.changecircleImageView);
        imaadd1=(ImageView)findViewById(R.id.imaadd1);
        imaadd2=(ImageView)findViewById(R.id.imaadd2);
        imaadd3=(ImageView)findViewById(R.id.imaadd3);
        changeimaadd1=(ImageView)findViewById(R.id.changeimaadd1);
        changeimaadd2=(ImageView)findViewById(R.id.changeimaadd2);
        changeimaadd3=(ImageView)findViewById(R.id.changeimaadd3);
        btn_touxiang=(Button)findViewById(R.id.btn_touxiang);
        btn_imaadd1=(Button)findViewById(R.id.btn_imaadd1);
        btn_imaadd2=(Button)findViewById(R.id.btn_imaadd2);
        btn_imadd3=(Button)findViewById(R.id.btn_imaadd3);
        btn_change=(Button)findViewById(R.id.btn_change);
        Bundle bundle=getIntent().getBundleExtra("data1");
        cropchangeName=bundle.getString("cropName");
        edit_cropname.setText(cropchangeName);
        edit_cropPrice.setText(bundle.getString("cropPrice"));
        edit_cropKucun.setText(bundle.getString("cropKucun"));
        edit_cropYunfei.setText(bundle.getString("cropYunfei"));
        edit_cropFahuodi.setText(bundle.getString("cropFahuodi"));
        edit_cropUsername.setText(bundle.getString("cropUsername"));
        edit_cropDescribe.setText(bundle.getString("cropDescribe"));
        imageview1=bundle.getString("imaAdd1");
        imageview2=bundle.getString("imaAdd2");
        imageview3=bundle.getString("imaAdd3");
        imageview=bundle.getString("imaTouxiang");
        Glide.with(ChangeActivity.this).load(bundle.getString("imaTouxiang")).into(circleImageView);
        Glide.with(ChangeActivity.this).load(bundle.getString("imaAdd1")).into(imaadd1);
        Glide.with(ChangeActivity.this).load(bundle.getString("imaAdd2")).into(imaadd2);
        Glide.with(ChangeActivity.this).load(bundle.getString("imaAdd3")).into(imaadd3);
        btn_touxiang.setOnClickListener(this);
        btn_imaadd1.setOnClickListener(this);
        btn_imaadd2.setOnClickListener(this);
        btn_imadd3.setOnClickListener(this);
        btn_change.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_touxiang:
                ima=false;
                PictureSelector
                        .create(ChangeActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.btn_imaadd1:
                ima1=false;
                PictureSelector
                    .create(ChangeActivity.this, 1)
                    .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.btn_imaadd2:
                ima2=false;
                PictureSelector
                    .create(ChangeActivity.this, 2)
                    .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.btn_imaadd3:
                ima3=false;
                PictureSelector
                    .create(ChangeActivity.this, 3)
                    .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.btn_change:
                SendPostData();
                new XToast(ChangeActivity.this)
                        .setDuration(2000)
                        .setView(R.layout.toast_hint)
                        .setAnimStyle(android.R.style.Animation_Translucent)
                        .setImageDrawable(android.R.id.icon, R.mipmap.ic_dialog_tip_finish)
                        .setText(android.R.id.message, "修改成功请等待")
                        .show();
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {//延时time秒后，将运行如下代码
                        if (lag) {
                            finish();
                        }
                    }
                }, 2000);*/

                /*延迟2秒给Toast显示*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        });
                    }
                }).start();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            Toast.makeText(ChangeActivity.this,"请选择图片",Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode){
            case PictureSelector.SELECT_REQUEST_CODE:
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                changecircleImageView.setImageBitmap(ImageUtils.getBitmap(picturePath));
                changemyuri=bitmapToString(ImageUtils.getBitmap(picturePath));
                break;
            case 1:
                String picturePath1 = data.getStringExtra(PictureSelector.PICTURE_PATH);
                changeimaadd1.setImageBitmap(ImageUtils.getBitmap(picturePath1));
                changemyuri1=bitmapToString(ImageUtils.getBitmap(picturePath1));
                break;
            case 2:
                String picturePath2 = data.getStringExtra(PictureSelector.PICTURE_PATH);
                changeimaadd2.setImageBitmap(ImageUtils.getBitmap(picturePath2));
                changemyuri2=bitmapToString(ImageUtils.getBitmap(picturePath2));
                break;
            case 3:
                String picturePath3 = data.getStringExtra(PictureSelector.PICTURE_PATH);
                changeimaadd3.setImageBitmap(ImageUtils.getBitmap(picturePath3));
                changemyuri3=bitmapToString(ImageUtils.getBitmap(picturePath3));
                break;
                default:
                    break;
        }

        }

    private void SendPostData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                result= SendPost.sendPost(urlPath,PrepareData());
                Log.i(TAG,"返回结果"+result);
            }
        }).start();
    }


    private String PrepareData() {
        String content=null;
        try {
            JSONObject object1 = new JSONObject();
            object1.put("cropchangename", cropchangeName);
            object1.put("cropName", edit_cropname.getText().toString());
            object1.put("cropPrice", edit_cropPrice.getText().toString());
            object1.put("cropKucun", edit_cropKucun.getText().toString());
            object1.put("cropYunfei", edit_cropYunfei.getText().toString());
            object1.put("cropFahuodi", edit_cropFahuodi.getText().toString());
            object1.put("cropUsername", edit_cropUsername.getText().toString());
            object1.put("cropDescribe", edit_cropDescribe.getText().toString());
            if(ima1){
                object1.put("imaAdd1",  bitmapToString(netPicToBmp(imageview1)));
            }else{
                object1.put("imaAdd1",  changemyuri1);
            }
            if(ima2){
                object1.put("imaAdd2",  bitmapToString(netPicToBmp(imageview2)));
            }else{
                object1.put("imaAdd2",  changemyuri2);
            }
            if(ima3){
                object1.put("imaAdd3",  bitmapToString(netPicToBmp(imageview3)));
            }else{
                object1.put("imaAdd3", changemyuri3);
            }
            if(ima){
                object1.put("imaTouxiang",  bitmapToString(netPicToBmp(imageview)));
            }else{
                object1.put("imaTouxiang", changemyuri);
            }
            content = String.valueOf(object1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return content;

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


    /*网络图片转换为bitmap*/
    public static Bitmap netPicToBmp(String src) {
        try {
            Log.d("FileUtil", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            //设置固定大小
            //需要的大小
            float newWidth = 200f;
            float newHeigth = 200f;

            //图片大小
            int width = myBitmap.getWidth();
            int height = myBitmap.getHeight();

            //缩放比例
            float scaleWidth = newWidth / width;
            float scaleHeigth = newHeigth / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeigth);

            Bitmap bitmap = Bitmap.createBitmap(myBitmap, 0, 0, width, height, matrix, true);
            return bitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}
