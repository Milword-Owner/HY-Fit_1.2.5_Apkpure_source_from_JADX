package com.mob.tools.utils;

import android.content.Context;
import android.media.AudioManager;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;

public class AudioHelper {
    public static final int ALARM_RING = 4;
    public static final int CALL_RING = 2;
    public static final int MEDIA_RING = 3;
    public static final int SYSTEM_RING = 1;
    private static AudioHelper audioHelper;
    private AudioManager audioManager = ((AudioManager) this.context.getSystemService("audio"));
    private Context context;
    private int currentAlarmVoice;
    private int currentCommonVoice;
    private int currentMediaVoice;
    private int currentRingVoice;

    public static AudioHelper getInstance(Context context2) {
        if (audioHelper == null) {
            audioHelper = new AudioHelper(context2);
        }
        return audioHelper;
    }

    private AudioHelper(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public int getAudioMode() {
        try {
            int ringerMode = this.audioManager.getRingerMode();
            if (ringerMode == 0) {
                return 0;
            }
            if (ringerMode != 1) {
                return ringerMode != 2 ? 2 : 2;
            }
            return 1;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    private void getVioce() {
        int streamVolume = this.audioManager.getStreamVolume(1);
        if (streamVolume > 0) {
            this.currentCommonVoice = streamVolume;
        }
        int streamVolume2 = this.audioManager.getStreamVolume(2);
        if (streamVolume2 > 0) {
            this.currentRingVoice = streamVolume2;
        }
        int streamVolume3 = this.audioManager.getStreamVolume(3);
        if (streamVolume3 > 0) {
            this.currentMediaVoice = streamVolume3;
        }
        int streamVolume4 = this.audioManager.getStreamVolume(4);
        if (streamVolume4 > 0) {
            this.currentAlarmVoice = streamVolume4;
        }
        NLog instance = MobLog.getInstance();
        instance.mo29768d("System Ring：" + streamVolume + " Call Ring：" + streamVolume2 + " Media Ring：" + streamVolume3 + "Alarm Ring：" + streamVolume4, new Object[0]);
    }

    public void setQuietMode(int i) {
        try {
            getVioce();
            if (this.audioManager.getStreamVolume(i) != 0) {
                this.audioManager.setStreamVolume(i, 0, 8);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    public void setSilentMode() {
        this.audioManager.setStreamVolume(2, 0, 8);
        this.audioManager.setRingerMode(0);
    }

    public void recoveryVoice(int i) {
        this.audioManager.setStreamVolume(i, getLastVoice(i), 8);
    }

    public void addVoice(int i, int i2) {
        this.audioManager.adjustStreamVolume(i, i2, 8);
    }

    private int getLastVoice(int i) {
        int i2;
        if (i == 1) {
            i2 = this.currentCommonVoice;
        } else if (i == 3) {
            i2 = this.currentMediaVoice;
        } else if (i == 2) {
            i2 = this.currentRingVoice;
        } else {
            i2 = i == 4 ? this.currentAlarmVoice : 0;
        }
        NLog instance = MobLog.getInstance();
        instance.mo29768d("Voice Type:" + i + "Last Count:" + i2, new Object[0]);
        return i2;
    }

    public boolean checkMusicActivity() {
        boolean isMusicActive = this.audioManager.isMusicActive();
        NLog instance = MobLog.getInstance();
        instance.mo29768d("Music Action:" + isMusicActive, new Object[0]);
        return isMusicActive;
    }
}
