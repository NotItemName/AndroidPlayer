package com.player;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.player.persist.dao.ArtistDao;
import com.player.service.Player;
import com.player.service.cursor.CursorAdapterHelper;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Николай
 */
@EFragment(R.layout.list_fragment)
public class SongListFragment extends ListFragment {

    @Bean
    ArtistDao artistDao;
    @Bean
    Player player;
    @Bean
    CursorAdapterHelper adapterHelper;
    TextView header;
    String[] points = new String[]{
            "Playlists", "Artists", "Albums", "Songs", "Genres"
    };
    private LinkedList<Long> path = new LinkedList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_expandable_list_item_1, points);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position != 0 && isEnd()) {
            player.setMediaPlayers(Arrays.asList(id));
            player.play();
            return;
        }

        if (position == 0 && path.size() != 0) {
            path.removeLast();
            l.invalidate();

            if (path.size() == 0) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_expandable_list_item_1, points);
                l.removeHeaderView(header);
                setListAdapter(adapter);
            } else {
                CursorAdapter adapter = adapterHelper.getCursorAdapter(path);
                l.setAdapter(adapter);
            }
            return;
        }

        path.addLast(id);

        l.invalidate();
        setHeader(l);

        CursorAdapter adapter = adapterHelper.getCursorAdapter(path);
        l.setAdapter(adapter);

        super.onListItemClick(l, v, position, id);
    }

    private void setHeader(ListView l) {
        if (l.getHeaderViewsCount() < 1 && path.size() > 0) {
            header = new TextView(getActivity());
            header.append("\n\t...\n");
            l.addHeaderView(header);
        }
    }

    private boolean isEnd() {
        if (path.size() > 0) {
            Long first = path.getFirst();

            return (first == 3 && path.size() == 1)
                    || (first == 2 && path.size() == 2)
                    || (first == 1 && path.size() == 3);
        } else {
            return false;
        }

    }
}
