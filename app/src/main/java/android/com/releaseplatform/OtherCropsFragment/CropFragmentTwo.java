package android.com.releaseplatform.OtherCropsFragment;


import android.com.releaseplatform.RecyclerView.Bean;
import android.com.releaseplatform.RecyclerView.BeanAdapter;
import android.com.releaseplatform.StorageCrop.DaoguActivity;
import android.com.releaseplatform.StorageCrop.HeidouActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.releaseplatform.R;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CropFragmentTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CropFragmentTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView recyclerView;
    private List<Bean> beanList=new ArrayList<>();
    private BeanAdapter beanAdapter;

    public CropFragmentTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CropFragmentTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static CropFragmentTwo newInstance(String param1) {
        CropFragmentTwo fragment = new CropFragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crop_fragment_one, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        AddData();
        beanAdapter=new BeanAdapter(beanList);
        recyclerView.setAdapter(beanAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        beanAdapter.setOnItemClickListener(new BeanAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if(position==0) {
                    Intent intent0 = new Intent(getActivity(), DaoguActivity.class);
                    startActivity(intent0);
                }else{
                    Toast.makeText(getActivity(),"暂无信息（第一个有）",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void AddData(){
        if(beanList.size()>0){
            beanList.clear();
        }
        Bean bean=new Bean("稻谷",R.drawable.pic_search4);
        Bean bean1=new Bean("大麦米",R.drawable.pic_othercrop4);
        Bean bean2=new Bean("白芝麻",R.drawable.pic_othercrop3);
        beanList.add(bean);
        beanList.add(bean1);
        beanList.add(bean2);


    }


}
