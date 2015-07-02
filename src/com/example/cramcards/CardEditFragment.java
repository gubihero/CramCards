package com.example.cramcards;

import java.util.UUID;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CardEditFragment extends Fragment {
	private Card mCard;
	private TextView mFront;
	private TextView mBack;
	private Button mSaveButton;
	
	private UUID deckId;
	private UUID cardId;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		deckId = (UUID)getArguments().getSerializable("deckID");
		cardId = (UUID)getArguments().getSerializable("cardID");
		
		mCard = DeckBox.get(getActivity()).getDeckCard(deckId,cardId);
		
	}
	
	
	public static CardEditFragment newInstance(UUID deckId ,UUID cardId) {
		Bundle args = new Bundle();
		args.putSerializable("cardID", cardId);
		args.putSerializable("deckID", deckId);
		
		CardEditFragment fragment = new CardEditFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_edit_card, parent,false);
		
		mFront=(TextView)v.findViewById(R.id.edit_front);
		//decide if I want to make a decision here
		if(mCard.getFront().length() != 0) {
			mFront.setText(mCard.getFront());
		}
		mFront.addTextChangedListener(new TextWatcher() {
	        @Override
	        public void afterTextChanged(Editable arg0) {
	        	mCard.setFront(mFront.getText().toString());
	        }
	        
	        @Override 
	        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	        	
	        }
	        
	        @Override
	        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	        	
	        }
	    });
		
		
		mBack=(TextView)v.findViewById(R.id.edit_back);
		//decide if I want to make a decision here
		if(mCard.getBack().length() != 0) {
			mBack.setText(mCard.getBack());
		}
		mBack.addTextChangedListener(new TextWatcher() {
	        @Override
	        public void afterTextChanged(Editable arg0) {
	        	mCard.setBack(mBack.getText().toString());
	        }
	        
	        @Override 
	        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	        	
	        }
	        
	        @Override
	        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	        	
	        }
	    });
		mSaveButton = (Button)v.findViewById(R.id.save_button);
		mSaveButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
			}
		});
		return v;
	}
	
	
}
