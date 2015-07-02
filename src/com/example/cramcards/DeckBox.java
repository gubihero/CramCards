package com.example.cramcards;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class DeckBox {
	private static DeckBox sDeckBox;
    private Context mAppContext;
    
    private ArrayList<Deck> mDecks;

    private DeckBox(Context appContext) {
        mAppContext = appContext;
        mDecks = new ArrayList<Deck>();
    }

    public static DeckBox get(Context c) {
        if (sDeckBox == null) {
        	sDeckBox = new DeckBox(c.getApplicationContext());
        }
        return sDeckBox;
    }

	public ArrayList<Deck> getDecks() {
		return mDecks;
	}

	public void setDecks(ArrayList<Deck> mDecks) {
		this.mDecks = mDecks;
	}
	
	public Deck getDeckAt(int pos){
		return this.mDecks.get(pos);
	}
	
	public void addDeck(Deck deck){
		this.mDecks.add(deck);
	}
	
	public void addDeckCard(UUID deckId, Card card){
		for (Deck d : mDecks) {
            if (d.getId().equals(deckId))
                d.addCard(card);
        }
	}
	
	public Deck getDeck(UUID id) {
		 for (Deck d : mDecks) {
	            if (d.getId().equals(id))
	                return d;
	        }
	        return null;
	}
	
	public Card getDeckCard(UUID deckid, UUID cardid){
		for (Deck d : mDecks) {
            if (d.getId().equals(deckid)){
            	for (Card c : d.getCards()) {
            		if(c.getId().equals(cardid)){
            			return c;
            		}
            	}
            }
        }
        return null;
	}
	
	public void removeDeck(UUID deckid){
		for (int i = 0; i<mDecks.size(); i++) {
			if(mDecks.get(i).getId().equals(deckid)){
				mDecks.remove(i);
				break;
			}
		}
		
	}
	
	public void removeDeckCard(UUID deckid, UUID cardid){
		for (int i = 0; i<mDecks.size(); i++) {
			if(mDecks.get(i).getId().equals(deckid)){
				for (int j = 0; j<mDecks.get(i).getCards().size(); j++) {
					if(mDecks.get(i).getCards().get(j).getId().equals(cardid)){
						mDecks.get(i).getCards().remove(j);
						break;
					}
				}
				break;
			}
		}
	}
}
