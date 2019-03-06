package android.com.releaseplatform.RecyclerView;

import android.com.releaseplatform.R;
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BeanAdapter extends RecyclerView.Adapter<BeanAdapter.ViewHolder> {

    private Context mContext;
    private List<Bean> mBeanList;
    private OnItemClickListener monItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView beanImage;
        TextView beanName;

        public ViewHolder(View view) {
            super(view);
            beanImage=(ImageView)view.findViewById(R.id.bean_image);
            beanName=(TextView)view.findViewById(R.id.bean_name);
        }
    }

    public BeanAdapter(List<Bean> BeanList) {
        mBeanList=BeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null) {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.bean_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.beanName.setText(mBeanList.get(position).getName());
        Glide.with(mContext).load(mBeanList.get(position).getImageId()).into(holder.beanImage);
        if(monItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public interface OnItemClickListener {
        void onClick( int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        monItemClickListener = onItemClickListener;
    }


}
