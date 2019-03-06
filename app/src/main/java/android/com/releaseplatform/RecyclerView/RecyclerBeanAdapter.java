package android.com.releaseplatform.RecyclerView;

import android.annotation.SuppressLint;
import android.com.releaseplatform.R;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerBeanAdapter extends RecyclerView.Adapter<RecyclerBeanAdapter.ViewHolder> {

    private Context mContext;
    private List<RecyclerBean> mrecyclerBeanList;
    private int mPosition = 0;
    private OnItemClickListener mOnItemClickListener;


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView beanName;
        LinearLayout linearLayout;
        public ViewHolder(View view) {
            super(view);
            beanName=(TextView)view.findViewById(R.id.recycler_name);
            linearLayout=view.findViewById(R.id.recycler_linearlayout);
        }
    }

    public RecyclerBeanAdapter(List<RecyclerBean> recyclerBeanList) {
        mrecyclerBeanList=recyclerBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null) {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.recyclerbean_item,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.beanName.setText(mrecyclerBeanList.get(position).getName());
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
        if (mPosition == position){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#E0EEEE"));
        }


    }


    public void setPosition(int position) {
        mPosition = position;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick( int position);
    }

    @Override
    public int getItemCount() {
        return mrecyclerBeanList.size();
    }

}
