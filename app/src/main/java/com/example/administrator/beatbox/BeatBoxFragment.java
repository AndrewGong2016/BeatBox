package com.example.administrator.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//FragmentBeatBoxBinding 类为 fragment_beat_box.xml 布局的数据绑定类
import com.example.administrator.beatbox.databinding.FragmentBeatBoxBinding;
//ListItemSoundBinding 为 list_item_sound.xml 布局使用的数据绑定类，同 FragmentBeatBoxBinding 一样，自动生成
import com.example.administrator.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {
    private static String TAG ="BeatBoxFragmentGuan";
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
         * 1 前一个Criminal应用使用[inflater]来加载指定 layout xml 中的所有视图组件
         * 2 然后使用view的find id 接口方法来找到RecyclerView
         * 但是
         * 这里的BeatBox应用中使用了数据绑定的“高级”方式来实例化RecycleView对象
         */

        FragmentBeatBoxBinding binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_beat_box, container,false);
        binding.recycleView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        binding.recycleView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        /**
         * getRoot()返回全部FragmentBeatBoxBinding对象中绑定的Root View ,即全部视图组件的容器
         * binding.recycleView 指向上述容器中的特定视图对象——recycleView
         */
        return binding.getRoot();
    }


    /**
     * SoundHolder中借助数据绑定类完成 ViewModel 的初始化工作
     */
    private class SoundHolder extends RecyclerView.ViewHolder{
        ListItemSoundBinding mBinding;
        private SoundHolder(ListItemSoundBinding binding){
            /**
             * 把视图实例化的工作全部交给bingding对象
             */
            super(binding.getRoot());//View给父类构造器
            mBinding = binding;

            /**
             * 使用bingding 为视图层（V） 配置 ViewModel 对象（V_M）！
             * ###视图与模型数据通过ViewModel进行沟通和对话###
             */
            mBinding.setViewModel(new SoundViewModel(mBeatBox));


            /**
             * 对比之前的CrimeHolder中代码：
             * 使用继承自ViewHolder的itemView（itemView 与bingding.getRoot 一致，指向布局的根。）；
             * 然后使用新增的bind()接口进行视图的更新。
             */
            /*
            itemView.findViewById(R.id.xxx);
            */

        }

        public void bind(Sound sound) {
            //使用ViewModel 来更新模型数据
            /**
             * 运用V_M 为视图层（V）设置模型数据（M）
             * 此时，ViewModel进行了更新，【但是】【但是】【布局xml并不知情】
             *
             * 【注意】：在之前的crime应用中，是直接使用视图对象进行更新视图的：
             * TextView title_date=(TextView)itemView.findViewById(R.id.xxx);
             * title_date.setText(crime.getDate().toString());
             */
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }

    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{


        //$$$$需要模型层数据来构造Adapter
        List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            /**
             * 同上，也使用DataBindingUtil 生成的Binding对象来构建视图所需的Holder
             */
            ListItemSoundBinding soundBinding = DataBindingUtil.inflate(inflater,R.layout.list_item_sound,viewGroup,false);


            SoundHolder soundHolder = new SoundHolder(soundBinding);
            Log.d(TAG, "onCreateViewHolder: "+soundHolder.toString());

            return soundHolder;
        }

        //将模型层数据绑定到Holder
        @Override
        public void onBindViewHolder(@NonNull SoundHolder soundHolder, int position) {
            Sound sound = mSounds.get(position);
            soundHolder.bind(sound);
            Log.d(TAG, "onBindViewHolder: "+soundHolder.toString());
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }


}

