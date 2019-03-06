package android.com.releaseplatform;

import android.app.AlertDialog;
import android.com.releaseplatform.db.PointPeople;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Date;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class OpinionActivity extends AppCompatActivity {

    private ImageView imageView;
    private ListView listView;
    private List<String> dataList=new ArrayList<>();
    private   ArrayAdapter<String> adapter;
    private List<PointPeople> allMessage;
    private Date dt;
    private String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        if(Build.VERSION.SDK_INT >= 21)
        {
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        listView=(ListView) findViewById(R.id.list_dialog);
        adapter=new ArrayAdapter<String>(OpinionActivity.this, android.R.layout.simple_list_item_1, dataList);
        imageView=(ImageView)findViewById(R.id.imageview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        allMessage = DataSupport.findAll(PointPeople.class);
        if(allMessage.size()>0){
            dataList.clear();
            for(PointPeople pointPeople1:allMessage)
            {
                dataList.add(pointPeople1.getMessage()+"          - - - - - - -"+pointPeople1.getTime());
            }
            adapter.notifyDataSetChanged();
        }
        listView.setAdapter(adapter);
    }




    private void showDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.opinion_dialog_layout,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();

        Button btn_cancel_high_opion = view.findViewById(R.id.btn_cancel_high_opion);
        Button btn_agree_high_opion = view.findViewById(R.id.btn_agree_high_opion);
        final EditText editText=view.findViewById(R.id.edittext);
        editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText.setGravity(Gravity.TOP);
        editText.setSingleLine(false);
        editText.setHorizontallyScrolling(false);

        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OpinionActivity.this,"已提交",Toast.LENGTH_SHORT).show();
                time=new Date().toLocaleString();
                PointPeople pointPeople=new PointPeople();
                pointPeople.setMessage(editText.getText().toString());
                pointPeople.setTime(time);
                pointPeople.save();
                allMessage = DataSupport.findAll(PointPeople.class);
                if(allMessage.size()>0){
                   dataList.clear();
                   for(PointPeople pointPeople1:allMessage)
                   {
                       dataList.add(pointPeople1.getMessage()+"          - - - - - - -"+pointPeople1.getTime());
                   }
                    adapter.notifyDataSetChanged();
                }
                //... To-do
                dialog.dismiss();
            }
        });
        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的占满，高度占屏幕的3/4
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this)), ScreenUtils.getScreenHeight(this)/4*3);
    }



}
