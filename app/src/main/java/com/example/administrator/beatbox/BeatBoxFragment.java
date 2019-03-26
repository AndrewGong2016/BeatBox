package com.example.administrator.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.beatbox.databinding.FragmentBeatBoxBinding;

public class BeatBoxFragment extends Fragment {


    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
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

        /**
         * getRoot()返回全部的FragmentBeatBoxBinding对象中绑定的Root View ,即全部的视图组件容器
         * binding.recycleView 指向上述容器中的特定视图对象——recycleView
         */
        return binding.getRoot();
    }
}

