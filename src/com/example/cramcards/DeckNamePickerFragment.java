package com.example.cramcards;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class DeckNamePickerFragment extends DialogFragment {
	
	private String mName;
	private EditText mNamePicker;

    public static DeckNamePickerFragment newInstance() {
        Bundle args = new Bundle();
        DeckNamePickerFragment fragment = new DeckNamePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }
    
    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra("deckName", mName);

        getTargetFragment()
            .onActivityResult(getTargetRequestCode(), resultCode, i);
    }
	    
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		View v = getActivity().getLayoutInflater()
		    .inflate(R.layout.dialog_name, null);
		
		mName = "";
		
		mNamePicker = (EditText)v.findViewById(R.id.dialog_name_editText);
		mNamePicker.addTextChangedListener(new TextWatcher() {
	        @Override
	        public void afterTextChanged(Editable arg0) {
	        	mName = mNamePicker.getText().toString();
	        	getArguments().putSerializable("deckName", mName);
	        }
	        
	        @Override 
	        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	        	
	        }
	        
	        @Override
	        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	        	
	        }
	    });
		
        return new AlertDialog.Builder(getActivity())
        	.setView(v)
            .setTitle(R.string.deck_name_title)
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    sendResult(Activity.RESULT_OK);
                }
            })
            .create();
    }

}
