package com.example.cramcards;

import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class CardFragment extends Fragment {
	private static final int EDIT_CARD = 1;
	private Card mCard;
	private TextView mSideText;
	private Button mFlipButton;
	private CheckBox mMasteredBox;
	
	private UUID deckId;
	private UUID cardId;
	
	public void flip(){
		mCard.setFlipStatus(!mCard.isFlipStatus());
		if(mCard.isFlipStatus()){
			mSideText.setText(mCard.getBack());
		} else {
			mSideText.setText(mCard.getFront());
		}
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		deckId = (UUID)getArguments().getSerializable("deckID");
		cardId = (UUID)getArguments().getSerializable("cardID");
		
		mCard = DeckBox.get(getActivity()).getDeckCard(deckId,cardId);
		
	}
	
	@Override
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	     super.onCreateOptionsMenu(menu, inflater);
	     inflater.inflate(R.menu.fragment_card, menu);
	 }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_item_edit_card:
	            //probably should have a dialogue that asks for deck name
	            Intent i = new Intent(getActivity(), CardEditActivity.class);
	            i.putExtra("deckID", deckId);
	            i.putExtra("cardID", cardId);
	            startActivityForResult(i, EDIT_CARD);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_CARD) {
            // Handle result
        }
    }
	
	
	
	public static CardFragment newInstance(UUID deckId ,UUID cardId) {
		Bundle args = new Bundle();
		args.putSerializable("cardID", cardId);
		args.putSerializable("deckID", deckId);
		
		CardFragment fragment = new CardFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
    public void onResume() {
        super.onResume();
        if(mCard.isFlipStatus()){
        	mSideText.setText(mCard.getFront());
        } else {
        	mSideText.setText(mCard.getFront());
        }
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_card, parent,false);
		
		mSideText=(TextView)v.findViewById(R.id.side_text);
		//decide if I want to make a decision here
		mSideText.setText(mCard.getFront());
		
		mFlipButton = (Button)v.findViewById(R.id.flip_button);
		mFlipButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				flip();
			}
		});
		
		mMasteredBox = (CheckBox)v.findViewById(R.id.card_mastered);
		mMasteredBox.setChecked(mCard.isMastered());
		mMasteredBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		            // Set the crime's solved property
		            mCard.setMastered(isChecked);
		        }
		    });
		
		
		return v;
	}

}
