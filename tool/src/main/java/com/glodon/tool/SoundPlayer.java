package com.glodon.tool;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;


import java.util.HashMap;

/**
 * Created by zhangtianjie
 *
 * @email zhangtj-a@glodon.com
 * @date 2019/5/31 10:36 AM
 **/
public class SoundPlayer {

    private static SoundPlayer mSoundPlayer;


    public static SoundPlayer getInstance(Context context) {
        if (mSoundPlayer == null) {
            synchronized (SoundPlayer.class) {
                if (mSoundPlayer == null) {
                    mSoundPlayer = new SoundPlayer(context);
                }
            }
        }

        return mSoundPlayer;
    }

    private SoundPool mSoundPool = null;

    private SoundPlayer(Context context) {

        initSP(context);


    }


    private HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();

    private void initSP(Context context) {
        //设置最多可容纳5个音频流，音频的品质为5
        mSoundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
//        soundID.put(1, mSoundPool.load(context, R.raw.successin, 1));

//        soundID.put(2, mSoundPool.load(context, R.raw.successout, 1));



    }


    public void playInSound() {

        mSoundPool.play(soundID.get(1), 1, 1, 0, 0, 1);
    }

    public void playOutSound() {

        mSoundPool.play(soundID.get(2), 1, 1, 0, 0, 1);
    }


}
