package com.AJKH.SpotiQueue.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.AJKH.SpotiQueue.Firebase.DatabaseUtils;

import com.AJKH.SpotiQueue.MainActivity;
import com.AJKH.SpotiQueue.Constants;

public class ExistingSessionsFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] activeSession = DatabaseUtils.getInstance().getActiveSessions();
        builder.setTitle("Select a Session")
                .setItems(activeSession, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.print(activeSession[0]);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra(Constants.SESSION_ID, activeSession[which]);
                        intent.putExtra(Constants.ROLE, "user");
                        startActivity(intent);
                    }
                });
        return builder.create();
    }
}