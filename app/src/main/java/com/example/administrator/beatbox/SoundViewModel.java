package com.example.administrator.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * 继承Observable ，该类对象将可被观察，一旦数据发生改变，需要通知其注册的所有观察者
 * Q：为什么这个soundViewmodel 对象可以是一个viewmodel呢？
 * A：见xml 布局文件中的申明
 */
public class SoundViewModel extends BaseObservable {

    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public Sound getSound() {
        return mSound;
    }

    //get方法，可在视图层的xml中直接使用:
    //android:text:{@viewModel.title}
    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();

    }

}
