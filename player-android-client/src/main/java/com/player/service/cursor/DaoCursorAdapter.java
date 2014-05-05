package com.player.service.cursor;

import android.widget.CursorAdapter;

import java.util.LinkedList;

/**
 * @author Николай
 */
public interface DaoCursorAdapter {

    CursorAdapter getCursorAdapter(LinkedList<Long> pathPart);
}
