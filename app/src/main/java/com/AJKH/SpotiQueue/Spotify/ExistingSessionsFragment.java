package com.AJKH.SpotiQueue.Spotify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AJKH.SpotiQueue.R;

/**
 * Created by jsturm on 11/3/2016.
 */

public class ExistingSessionsFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.session_select, container, false);
    }
}
