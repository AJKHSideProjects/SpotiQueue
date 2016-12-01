package com.AJKH.SpotiQueue.Spotify;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AJKH.SpotiQueue.Firebase.DatabaseUtils;
import com.AJKH.SpotiQueue.R;

/**
 * Created by jsturm on 11/3/2016.
 */

public class ExistingSessionsFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a Session")
                .setItems(DatabaseUtils.getInstance().getActiveSessions(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return builder.create();
    }
}