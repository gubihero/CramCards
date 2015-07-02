package com.example.cramcards;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class CardPagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<Card>mCards;
	private Deck mDeck;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		//need to make sure that this gets the right deck of cards
		UUID cardId = (UUID)getIntent().getSerializableExtra("cardID");
		UUID deckId = (UUID)getIntent().getSerializableExtra("deckID");
		
		mDeck = DeckBox.get(this).getDeck(deckId);
		mCards = mDeck.getCards();
		
		FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mCards.size();
            }

            @Override
            public Fragment getItem(int pos) {
                Card card = mCards.get(pos);
                return CardFragment.newInstance(mDeck.getId(),card.getId());
            }
        });
        
        for(int i = 0; i< mCards.size();i++){
        	if(mCards.get(i).getId().equals(cardId)) {
        		mViewPager.setCurrentItem(i);
        		break;
        	}
        }
	}
	
	
}
