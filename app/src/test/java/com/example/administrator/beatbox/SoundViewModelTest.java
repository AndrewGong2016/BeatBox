package com.example.administrator.beatbox;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SoundViewModelTest {
    String TAG ="Guantbb";

    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {

        //使用mock 生成所需的虚拟对象，且该对象中所执行的方法和接受的参数将被 Mockito 跟踪
        mBeatBox = mock(BeatBox.class);

        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);

        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        //断言：getTitle 是否与msound.getName 相同
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        Sound sound = new Sound("df");
        System.out.println(sound.toString());
        mSubject.onButtonClicked();
        //验证动作 mBeatBox.play(mSound) 被执行了
//        verify(mBeatBox).play(mSound);
        verify(mBeatBox).play(sound);
    }

}