package com.player.service.cursor;

import android.widget.CursorAdapter;
import com.player.service.Player;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Николай
 */
@EBean
public class CursorAdapterHelper {

    @Bean(ArtistCursorAdapter.class)
    DaoCursorAdapter artistCursorAdapter;
    @Bean(SongCursorAdapter.class)
    DaoCursorAdapter songCursorAdapter;
    @Bean(AlbumCursorAdapter.class)
    DaoCursorAdapter albumCursorAdapter;

    HashMap<Long, DaoCursorAdapter> adapterMap;

    @Bean
    Player player;

    public CursorAdapterHelper() {
    }

    public CursorAdapter getCursorAdapter(LinkedList<Long> path) {
        initAdapterMap();

        DaoCursorAdapter cursorAdapter = adapterMap.get(path.getFirst());


        return cursorAdapter.getCursorAdapter(path);
    }

    private void initAdapterMap() {
        if (adapterMap == null) {
            adapterMap = new HashMap<>();
            adapterMap.put(1L, artistCursorAdapter);
            adapterMap.put(2L, albumCursorAdapter);
            adapterMap.put(3L, songCursorAdapter);
        }
    }
}
