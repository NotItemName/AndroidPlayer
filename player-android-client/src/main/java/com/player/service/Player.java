package com.player.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Николай
 */
@EBean
public class Player {

    private static HashMap<Long, MediaPlayer> mpSet = new HashMap<>();
    @RootContext
    Context context;

    public void setMediaPlayers(List<Long> songs) {
        mpSet.clear();
        for (Long i : songs) {
            MediaPlayer mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_RING);
            try {
                mp.setDataSource(context, Uri.parse("http://109.86.225.206/player/export/" + i));
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mpSet.remove(mp);
                        mp.stop();
                        mp.release();
                    }
                });
                mp.prepare();
                mpSet.put(i, mp);
            } catch (IOException e) {
                Log.e("PLAYER", "Error playing audio resource", e);
            }
        }
    }

    public void play(Long resId) {
        MediaPlayer player = mpSet.get(resId);
        if (player != null) {
            player.start();
        } else {
            Toast.makeText(context, "Nothing to play", Toast.LENGTH_SHORT).show();
        }
    }

    public void play() {
        MediaPlayer player = (MediaPlayer) mpSet.values().toArray()[0];
        if (player != null) {
            player.start();
        } else {
            Toast.makeText(context, "Nothing to play", Toast.LENGTH_SHORT).show();
        }
    }
}
