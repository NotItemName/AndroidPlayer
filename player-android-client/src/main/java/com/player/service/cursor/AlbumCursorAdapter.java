package com.player.service.cursor;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import com.player.persist.dao.AlbumDao;
import com.player.persist.dao.SongDao;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.LinkedList;

/**
 * @author Николай
 */
@EBean
public class AlbumCursorAdapter implements DaoCursorAdapter {
    @Bean
    AlbumDao albumDao;

    @Bean
    SongDao songDao;

    @RootContext
    Context context;

    @Override
    public CursorAdapter getCursorAdapter(LinkedList<Long> pathParts) {
        Cursor cursor = null;
        switch (pathParts.size()) {
            case 1: {
                cursor = albumDao.getAllAlbums();
                break;
            }
            case 2: {
                cursor = songDao.getSongsByAlbumId(pathParts.getLast());
            }
        }

        String[] columns = {"name"};
        int[] to = {android.R.id.text1};
        return new SimpleCursorAdapter(context, android.R.layout.simple_expandable_list_item_1,
                cursor, columns, to, 0);
    }
}
