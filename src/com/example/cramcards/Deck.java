package com.example.cramcards;

import java.util.ArrayList;
import java.util.UUID;

public class Deck {
	private String mTitle;
	private ArrayList <Card> mCards;
	private UUID mId;
	
	public Deck() {
		mTitle = "New Deck";
		mCards = new ArrayList<Card>();
		mId = UUID.randomUUID(); 
	}
	
	@Override
	public String toString() {
		if (mTitle.length()>=20){
			return mTitle.substring(0, 17)+"...";
		}
		return mTitle;
		
	}
	
	public UUID getId() {
		return mId;
	}


	public void setId(UUID mId) {
		this.mId = mId;
	}

	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	
	public ArrayList<Card> getCards() {
		return mCards;
	}
	
	public void setCards(ArrayList<Card> mCards) {
		this.mCards = mCards;
	}
	
	public void addCard(Card card){
		this.mCards.add(card);
	}
	
	public void removeCard(int position){
		this.mCards.remove(position);
	}
	
	public void setCardMastered(int pos, boolean mastered){
		this.mCards.get(pos).setMastered(mastered);
	}
	
	public Card getCard(UUID id) {
		 for (Card c : mCards) {
	            if (c.getId().equals(id))
	                return c;
	        }
	        return null;
	}

}
