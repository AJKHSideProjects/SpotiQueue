package com.AJKH.SpotiQueue.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.AJKH.SpotiQueue.Constants;
import com.AJKH.SpotiQueue.MainActivity;


public class CreateNewSessionFragment extends DialogFragment {
    private String sessionName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter New Session Name");

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                sessionName = input.getText().toString();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(Constants.SESSION_ID, sessionName);
                intent.putExtra(Constants.ROLE, Constants.OWNER);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });



        return builder.create();
    }
}
