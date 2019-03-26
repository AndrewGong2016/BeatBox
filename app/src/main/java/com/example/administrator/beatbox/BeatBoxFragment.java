package com.example.administrator.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.administrator.beatbox.databinding.ListItemSoundBinding;

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBeatBox = new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /**
         * 1 前一个Criminal应用使用[inflater]来加载指定layout xml中的所有视图组件
         * 2 然后使用view的find id 接口方法来找到RecyclerView
         * 但是
         * 这里的BeatBox应用中使用了数据绑定的“高级”方式来实例化RecycleView对象
         */
        FragmentBeatBoxBinding binding =   DataBindingUtil.inflate(inflater,R.layout.fragment_beat_box, container,false);
        binding.recycleView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        binding.recycleView.setAdapter(new SoundAdapter());

        /**
         * getRoot()返回全部的FragmentBeatBoxBinding对象中绑定的Root View ,即全部的视图组件容器
         * binding.recycleView 指向上述容器中的特定视图对象——recycleView
         */
        return binding.getRoot();
    }


    private class SoundHolder extends RecyclerView.ViewHolder{
        ListItemSoundBinding mBinding;
        private SoundHolder(ListItemSoundBinding binding){
            /**
             * 把视图实例化的工作全部交给bingding对象
             */
            super(binding.getRoot());
            mBinding = binding;
        }
    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            /**
             * 同上，使用DataBindingUtil 生成的Binding对象来构建视图所需的Holder
             */
            ListItemSoundBinding soundBinding = DataBindingUtil.inflate(inflater,R.layout.list_item_sound,viewGroup,false);
            return new SoundHolder(soundBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder soundHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}

