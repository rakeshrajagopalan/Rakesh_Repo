package com.metroscs.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.metroscs.data.Card;

@Repository
public class CardDAOImpl extends AbstractDao implements CardDAO {

	public void saveCard(Card card) {
		persist(card);
	}

	@SuppressWarnings("unchecked")
	public List<Card> getAllCards() {
		return getSession().createCriteria(Card.class).list();
	}

	public Card getCardByNumber(int cardId) {
		Criteria criteria = getSession().createCriteria(Card.class);
		criteria.add(Restrictions.eq("cardId", cardId));
		return (Card) criteria.uniqueResult();
	}

	public Card getCardByName(String cardHolderName) {
		Criteria criteria = getSession().createCriteria(Card.class);
		criteria.add(Restrictions.eq("cardHolderName", cardHolderName));
		return (Card) criteria.uniqueResult();
	}
	
	public void updateCard(Card card) {
		getSession().update(card);
	}
	
	public void topupCard(Card card) {
		updateCard(card);
	}

}
