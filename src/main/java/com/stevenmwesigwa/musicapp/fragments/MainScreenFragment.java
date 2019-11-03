package com.stevenmwesigwa.musicapp.fragments;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stevenmwesigwa.musicapp.R;
import com.stevenmwesigwa.musicapp.Songs;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenFragment extends Fragment {
    RelativeLayout hiddenBottomBarMainScreen = null;
    ImageButton playPauseButtonMainScreen = null;
    TextView songTitleMainScreen = null;
    // Contains 'RecyclerView' and the 'bottombar'
    RelativeLayout visibleLayout = null;
    // The view that will get displayed when there're no songs
    RelativeLayout noSongsMainScreen = null;
    RecyclerView contentMainRecyclerView = null;
    // Holds the 'context'
    Activity activity = null;


    public MainScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        hiddenBottomBarMainScreen = view.findViewById(R.id.hiddenBottomBarMainScreen);
        playPauseButtonMainScreen = view.findViewById(R.id.playPauseButtonMainScreen);
        songTitleMainScreen = view.findViewById(R.id.songTitleMainScreen);
        visibleLayout = view.findViewById(R.id.visibleLayout);
        noSongsMainScreen = view.findViewById(R.id.noSongsMainScreen);
        contentMainRecyclerView = view.findViewById(R.id.contentMainRecyclerView);

        return view;
    }


    /**
     * Gets called when the 'MainScreenFragment' is attached to the 'Activity'
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    /**
     * @param activity
     */
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    private List<Songs> getSongsFromDevice() {
        List songsList = new ArrayList();
        // Create 'ContentResolver' to access the database
        ContentResolver contentResolver = activity.getContentResolver();
        // Create a 'Uri' so that you would fetch a specific song. i.e searching for songs
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // Make a 'Cursor'
        // Use the 'ContentResolver' to query the database
// 'projection' arg is null - because we want to return ALL columns and not a specific one
        // 'queryArgs' arg is null - because we don't want any arguments inside our query
        Cursor songCursor = contentResolver.query(songUri, null, null, null);
//We have to get the columns and data that is fetched only when the cursor is not 'null'
        if ((songCursor != null) && songCursor.moveToFirst()) {
            final int id = songCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            final int title = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            final int artist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            final int data = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            final int dateAdded = songCursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED);
// Get data inside those columns. Add to the 'songsList'
            while (songCursor.moveToNext()) {
                final Long songId = songCursor.getLong(id);
                final String songTitle = songCursor.getString(title);
                final String songArtist = songCursor.getString(artist);
                final String songData = songCursor.getString(data);
                final Long songDateAdded = songCursor.getLong(dateAdded);
                songsList.add(new Songs(songId, songTitle, songArtist, songData, songDateAdded));
            }
        }

        return songsList;
    }
}
