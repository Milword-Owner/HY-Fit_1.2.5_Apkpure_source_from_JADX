package com.previewlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

public class GPVideoPlayerActivity extends Activity {
    VideoView videoView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2517R.C2521layout.activity_gpvideoplayer);
        this.videoView = (VideoView) findViewById(C2517R.C2520id.gpVideo);
        this.videoView.setVideoPath(getIntent().getStringExtra("url"));
        this.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Toast.makeText(GPVideoPlayerActivity.this, C2517R.string.Playback_failed, 0).show();
                return false;
            }
        });
        this.videoView.start();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.videoView.isPlaying()) {
            this.videoView.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.videoView.pause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.videoView.stopPlayback();
    }

    public static void startActivity(Context context, String str) {
        Intent intent = new Intent(context, GPVideoPlayerActivity.class);
        intent.putExtra("url", str);
        context.startActivity(intent);
    }
}
