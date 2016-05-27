package com.metroscs.dao;

import java.util.List;

import com.metroscs.data.Card;

public interface CardDAO {

	public void saveCard(Card card);
	
	public List<Card> getAllCards();
	
	public Card getCardByNumber(int cardId);
	
	public Card getCardByName(String cardHolderName);
	
	public void updateCard(Card card);
	
	public void topupCard(Card card);
	
}
