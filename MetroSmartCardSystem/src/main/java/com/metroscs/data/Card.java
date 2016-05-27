package com.metroscs.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Card")
public class Card implements Serializable {
	
	private static final long serialVersionUID = 3073986060627251763L;

	@Id
	@Column(name="CardId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cardId;
	
	@Column(name="CardHolderName")
	private String cardHolderName;
	
	@Column(name="CardHolderIdentityType")
	private String cardHolderIdentityType;
	
	@Column(name = "CardHolderIdentityValue")
	private String cardHolderIdentityValue;
	
	@Column(name="CardBalance")
	private float cardBalance;
	
	@Column(name="Blocked")
	private int blocked;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public float getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(float cardBalance) {
		this.cardBalance = cardBalance;
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	public String getCardHolderIdentityType() {
		return cardHolderIdentityType;
	}

	public void setCardHolderIdentityType(String cardHolderIdentityType) {
		this.cardHolderIdentityType = cardHolderIdentityType;
	}

	public String getCardHolderIdentityValue() {
		return cardHolderIdentityValue;
	}

	public void setCardHolderIdentityValue(String cardHolderIdentityValue) {
		this.cardHolderIdentityValue = cardHolderIdentityValue;
	}
	
}
