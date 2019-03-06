package android.com.releaseplatform;

import android.com.releaseplatform.ListView.ListBean;
import android.com.releaseplatform.ListView.MyListadapter;
import android.com.releaseplatform.ListView.SearchListBean;
import android.com.releaseplatform.ListView.Searchadapter;
import android.com.releaseplatform.OkHttpUtil.HttpUtil;
import android.com.releaseplatform.StorageCrop.DaoguActivity;
import android.com.releaseplatform.StorageCrop.DiguaActivity;
import android.com.releaseplatform.StorageCrop.HeidouActivity;
import android.com.releaseplatform.StorageCrop.HeimuerActivity;
import android.com.releaseplatform.StorageCrop.XiaomaiActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {


    private ListView list1,list0;
    private List<SearchListBean>mlist1=new ArrayList<>();
    private Searchadapter searchadapter;
    private MyListadapter myListadapter;
    private List<ListBean>mlist=new ArrayList<>();
    private EditText editText;
    private ImageView ima_sousuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_search);
        list1=(ListView)findViewById(R.id.list1);
        list0=(ListView)findViewById(R.id.list0);
        editText=(EditText)findViewById(R.id.edittext);
        getdata1();
        searchadapter=new Searchadapter(SearchActivity.this,mlist1);
        list1.setAdapter(searchadapter);
        myListadapter=new MyListadapter(SearchActivity.this,mlist);
        list0.setAdapter(myListadapter);
        list0.setDivider(null);


        ima_sousuo=(ImageView)findViewById(R.id.ima_sousuo);
        ima_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mlist.size()>0){
                    mlist.clear();
                }
                String data=editText.getText().toString();
                String url = "http://114.115.245.160/linyunpeng/search?crop_name="+data;
                getdata(url);

            }
        });
        list0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("cropName",mlist.get(i).getCropName());
                bundle.putString("cropPrice",mlist.get(i).getCropPrice());
                bundle.putString("cropKucun",mlist.get(i).getCropKucun());
                bundle.putString("cropYunfei",mlist.get(i).getCropYunfei());
                bundle.putString("cropFahuodi",mlist.get(i).getCropFahuodi());
                bundle.putString("cropUsername",mlist.get(i).getCropUsername());
                bundle.putString("cropDescribe",mlist.get(i).getCropDescribe());
                bundle.putString("imaAdd1",mlist.get(i).getImaAdd1());
                bundle.putString("imaAdd2",mlist.get(i).getImaAdd2());
                bundle.putString("imaAdd3",mlist.get(i).getImaAdd3());
                bundle.putString("imaTouxiang",mlist.get(i).getImaTouxiang());
                Intent intent = new Intent(SearchActivity.this, GoodsActivity.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent0=new Intent(SearchActivity.this, HeidouActivity.class);
                    startActivity(intent0);
                }
                if(i==1){
                    Intent intent0=new Intent(SearchActivity.this, XiaomaiActivity.class);
                    startActivity(intent0);
                }
                if(i==2){
                    Intent intent0=new Intent(SearchActivity.this, DiguaActivity.class);
                    startActivity(intent0);
                }
                if(i==3){
                    Intent intent0=new Intent(SearchActivity.this, DaoguActivity.class);
                    startActivity(intent0);
                }
                if(i==4){
                    Intent intent0=new Intent(SearchActivity.this, HeimuerActivity.class);
                    startActivity(intent0);
                }
            }
        });
    }


        private void getdata1(){
           SearchListBean bean1=new SearchListBean();
           bean1.setCrop_name("黑豆");
           bean1.setCrop_price("¥8.9");
           bean1.setCrop_describe("产品描述：黑豆蛋白质含量36%，易于消化，对满足人体对蛋白质的需要具有重要意义");
           bean1.setIma_add1(R.drawable.pic_search1);
           mlist1.add(bean1);
           SearchListBean bean2=new SearchListBean();
           bean2.setCrop_name("小麦");
           bean2.setCrop_price("¥79");
           bean2.setCrop_describe("产品描述：小麦是小麦系植物的统称，是单子叶植物，是一种在世界各地广泛种植的禾本科植物，小麦的颖果是人类的主食之一，磨成面粉后可制作面包、馒头、饼干、面条等食物，发酵后可制成啤酒、酒精、白酒（如伏特加），或生质燃料。");
           bean2.setIma_add1(R.drawable.pic_search2);
           mlist1.add(bean2);
           SearchListBean bean3=new SearchListBean();
           bean3.setCrop_name("地瓜");
           bean3.setCrop_price("¥21.9");
           bean3.setCrop_describe("产品描述：地瓜，味甘性温，能滑肠通便，健胃益气。含有较多的纤维素， 能在肠中吸收水份增大粪便的体积，引起通便的作用。");
           bean3.setIma_add1(R.drawable.pic_search3);
           mlist1.add(bean3);
           SearchListBean bean4=new SearchListBean();
           bean4.setCrop_name("稻谷");
           bean4.setCrop_price("¥18.6");
           bean4.setCrop_describe("产品描述：稻谷，是指没有去除稻壳的子实，在植物学上属禾本科稻属普通栽培稻亚属中的普通稻亚种。");
           bean4.setIma_add1(R.drawable.pic_search4);
           mlist1.add(bean4);
           SearchListBean bean5=new SearchListBean();
           bean5.setCrop_name("黑木耳");
           bean5.setCrop_price("¥6.6");
           bean5.setCrop_describe("产品描述：具有滋补、润燥、养血益胃、活血止血、润肺、润肠的作用。");
           bean5.setIma_add1(R.drawable.pic_search5);
           mlist1.add(bean5);

        }


  private void getdata(String url){

      HttpUtil.sendOkHttpRequest(url, new Callback() {
          @Override
          public void onResponse(Call call, Response response) throws IOException {
              String responseText = response.body().string();
              try{
                  ListBean listBean=new ListBean();
                  JSONObject jsonObject1=new JSONObject(responseText);
                  String cropName = jsonObject1.getString("cropName");
                  String cropPrice = jsonObject1.getString("cropPrice");
                  String cropDescribe = jsonObject1.getString("cropDescribe");
                  String cropKucun = jsonObject1.getString("cropKucun");
                  String cropYunfei = jsonObject1.getString("cropYunfei");
                  String cropFahuodi = jsonObject1.getString("cropFahuodi");
                  String cropUsername = jsonObject1.getString("cropUsername");
                  String imaAdd1 = jsonObject1.getString("imaAdd1");
                  String imaAdd2 = jsonObject1.getString("imaAdd2");
                  String imaAdd3 = jsonObject1.getString("imaAdd3");
                  String imaTouxiang = jsonObject1.getString("imaTouxiang");
                  listBean.setCropName(cropName);
                  listBean.setCropPrice(cropPrice);
                  listBean.setCropDescribe(cropDescribe);
                  listBean.setCropKucun(cropKucun);
                  listBean.setCropYunfei(cropYunfei);
                  listBean.setCropFahuodi(cropFahuodi);
                  listBean.setCropUsername(cropUsername);
                  listBean.setImaAdd1(imaAdd1);
                  listBean.setImaAdd2(imaAdd2);
                  listBean.setImaAdd3(imaAdd3);
                  listBean.setImaTouxiang(imaTouxiang);
                  mlist.add(listBean);
              }catch (Exception e){
                  e.printStackTrace();
              }
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      myListadapter.notifyDataSetChanged();
                  }
              });



          }
          @Override
          public void onFailure(Call call, IOException e) {

          }


      });
  }




}
