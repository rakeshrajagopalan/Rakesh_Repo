package com.metroscs.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Travel")
public class Travel implements Serializable {

	private static final long serialVersionUID = -4081337934292833789L;

	@Id
	@Column(name = "TravelId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int travelId;

	@Column(name = "CardId")
	private int cardId;

	@Column(name = "FromStation")
	private int fromStation;

	@Column(name = "ToStation")
	private int toStation;

	@Column(name = "StartTime")
	private Timestamp startTime;

	@Column(name = "EndTime")
	private Timestamp endTime;
	
	@Column(name="BaseCharge") 
	private float baseCharge;
	
	@Column(name="TravelCharges")
	private float travelCharges;

	public int getTravelId() {
		return travelId;
	}

	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getFromStation() {
		return fromStation;
	}

	public void setFromStation(int fromStation) {
		this.fromStation = fromStation;
	}

	public int getToStation() {
		return toStation;
	}

	public void setToStation(int toStation) {
		this.toStation = toStation;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public float getTravelCharges() {
		return travelCharges;
	}

	public void setTravelCharges(float travelCharges) {
		this.travelCharges = travelCharges;
	}

	public float getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(float baseCharge) {
		this.baseCharge = baseCharge;
	}
	
}
