package com.example.administrator.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * 继承Observable ，该类对象将可被观察，一旦数据发生改变，需要通知其注册的所有观察者
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
