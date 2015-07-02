package com.example.cramcards;

import java.util.UUID;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class CardEditActivity extends SingleFragmentActivity {
	@Override
	protected Fragment createFragment() {
		UUID cardId = (UUID)getIntent().getSerializableExtra("cardID");
		UUID deckId = (UUID)getIntent().getSerializableExtra("deckID");
		
		return CardEditFragment.newInstance(deckId, cardId);
		
	}
}
