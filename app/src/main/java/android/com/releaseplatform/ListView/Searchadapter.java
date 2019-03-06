package android.com.releaseplatform.ListView;

import android.com.releaseplatform.R;
import android.com.releaseplatform.StorageCrop.HeidouActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Searchadapter extends BaseAdapter {


    private List<SearchListBean> mlist;
    private Context mcontext;

    public Searchadapter(Context mcontext, List<SearchListBean>mlist){
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

        viewHolder.cropName.setText(mlist.get(i).getCrop_name());
        viewHolder.cropPrice.setText(mlist.get(i).getCrop_price());
        viewHolder.cropDescribe.setText(mlist.get(i).getCrop_describe());
        Glide.with(mcontext).load(mlist.get(i).getIma_add1()).into(viewHolder.imaAdd1);


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
