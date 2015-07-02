package com.example.cramcards;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class CardListActivity extends SingleFragmentActivity {
	private static final String TAG = "CardListActivity";

	@Override
	protected Fragment createFragment() {
		UUID deckId = (UUID)getIntent().getSerializableExtra("deckID");
		
		return CardListFragment.newInstance(deckId);
	}

}
