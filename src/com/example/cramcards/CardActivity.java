package com.example.cramcards;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class CardActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID cardId = (UUID)getIntent().getSerializableExtra("cardID");
		UUID deckId = (UUID)getIntent().getSerializableExtra("deckID");
		
		return CardFragment.newInstance(deckId, cardId);
		
	}

	

}
