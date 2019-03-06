package android.com.releaseplatform.ListView;

import android.app.Activity;
import android.com.releaseplatform.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjq.xtoast.XToast;

import java.util.List;

public class MyListadapter extends BaseAdapter {


    private List<ListBean> mlist;
    private Context mcontext;

    public MyListadapter(Context mcontext,List<ListBean>mlist){
        this.mcontext=mcontext;
        this.mlist=mlist;
    }



    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return  i ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder=new ViewHolder();
            view =LayoutInflater.from(mcontext).inflate(R.layout.list_item, null);
            viewHolder.cropName=(TextView)view.findViewById(R.id.tet_cropname);
            viewHolder.cropPrice=(TextView)view.findViewById(R.id.tet_cropprice);
            viewHolder.cropDescribe=(TextView)view.findViewById(R.id.tet_cropdescribe);
            viewHolder.imaAdd1=(ImageView)view.findViewById(R.id.ima_add1);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.cropName.setText(mlist.get(i).getCropName());
        viewHolder.cropPrice.setText("¥"+mlist.get(i).getCropPrice());
        viewHolder.cropDescribe.setText("产品描述："+mlist.get(i).getCropDescribe());
        Glide.with(mcontext).load(mlist.get(i).getImaAdd1()).into(viewHolder.imaAdd1);
        if(mlist.get(i).getCropName()=="null"){
            new XToast((Activity) mcontext)
                    .setDuration(2000)
                    .setView(R.layout.toast_hint)
                    .setAnimStyle(android.R.style.Animation_Translucent)
                    .setImageDrawable(android.R.id.icon, R.mipmap.ic_dialog_tip_error)
                    .setText(android.R.id.message, "暂无数据，请重新搜索")
                    .show();
        }

        return view;
    }

    class ViewHolder{
        public TextView cropName;
        public TextView cropPrice;
        public TextView cropKucun;
        public TextView cropYunfei;
        public TextView cropFahuodi;
        public TextView cropUsername;
        public TextView cropDescribe;
        public ImageView imaAdd1;
        public ImageView imaAdd2;
        public ImageView imaAdd3;
        public ImageView imaTouxiang;

    }


}
