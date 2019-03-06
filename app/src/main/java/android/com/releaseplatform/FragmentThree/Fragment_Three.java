package android.com.releaseplatform.FragmentThree;

import android.app.ProgressDialog;
import android.com.releaseplatform.ChangeActivity;
import android.com.releaseplatform.GoodsActivity;
import android.com.releaseplatform.ListView.ListBean;
import android.com.releaseplatform.ListView.MyListadapter;
import android.com.releaseplatform.OkHttpUtil.HttpUtil;
import android.com.releaseplatform.R;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Fragment_Three extends Fragment {


    private ListView list;
    private List<ListBean> mlist= new ArrayList<>();
    private MyListadapter myListadapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    public ProgressDialog progressDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment__three, container, false);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        list = (ListView) view.findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        showProgressDialog();
        Utility();
        myListadapter = new MyListadapter(getActivity(), mlist);
        list.setAdapter(myListadapter);
        registerForContextMenu(list);//为ListView的所有item注册ContextMenu
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            list.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    View firstView = view.getChildAt(firstVisibleItem);
                    if(firstVisibleItem ==0 && (firstView == null || firstView.getTop() == 0))
                    {
                        swipeRefreshLayout.setEnabled(true);
                    }
                    else
                    {
                        swipeRefreshLayout.setEnabled(false);
                    }
                }
            });
            }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(mlist.size()>0){
                    mlist.clear();
                }
                Utility();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                Intent intent = new Intent(getActivity(), GoodsActivity.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });

    }


    private void Utility() {
        //String url = "https://rabtman.com/api/v2/acgclub/category/cosplay/pictures";
                String url = "http://114.115.245.160/linyunpeng/add";
                HttpUtil.sendOkHttpRequest(url, new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseText = response.body().string();
                        try {
                            JSONArray  jsonArray = new JSONArray(responseText);
                            for (int i = 0 ; i < jsonArray.length(); i++) {
                                ListBean listBean = new ListBean();
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
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
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myListadapter.notifyDataSetChanged();
                                closeProgressDialog();

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "请检查网路连接刷新后再试...", Toast.LENGTH_SHORT).show();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                });
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,2,Menu.NONE,"修改");
        menu.add(0,3,Menu.NONE,"删除");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int  pos=(int)list.getAdapter().getItemId(info.position);
        switch (item.getItemId()) {
            case 2:
                Bundle bundle = new Bundle();
                bundle.putString("cropName",mlist.get(pos).getCropName());
                bundle.putString("cropPrice",mlist.get(pos).getCropPrice());
                bundle.putString("cropKucun",mlist.get(pos).getCropKucun());
                bundle.putString("cropYunfei",mlist.get(pos).getCropYunfei());
                bundle.putString("cropFahuodi",mlist.get(pos).getCropFahuodi());
                bundle.putString("cropUsername",mlist.get(pos).getCropUsername());
                bundle.putString("cropDescribe",mlist.get(pos).getCropDescribe());
                bundle.putString("imaAdd1",mlist.get(pos).getImaAdd1());
                bundle.putString("imaAdd2",mlist.get(pos).getImaAdd2());
                bundle.putString("imaAdd3",mlist.get(pos).getImaAdd3());
                bundle.putString("imaTouxiang",mlist.get(pos).getImaTouxiang());
                Intent intent = new Intent(getActivity(), ChangeActivity.class);
                intent.putExtra("data1",bundle);
                startActivity(intent);

                break;
            case 3:
                String url = "http://114.115.245.160/linyunpeng/delete?crop_name="+mlist.get(pos).getCropName();
                HttpUtil.sendOkHttpRequest(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                });
                mlist.remove(pos);
                myListadapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "已删除该产品",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return  true;
    }


}
