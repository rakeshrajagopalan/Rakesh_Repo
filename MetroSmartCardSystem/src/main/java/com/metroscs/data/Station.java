package com.metroscs.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Station")
public class Station implements Serializable {
	
	private static final long serialVersionUID = -9214305907496346376L;

	@Id
	@Column(name="StationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;
	
	@Column(name="StationName")
	private String stationName;
	
	@Column(name="StationOrder")
	private int stationOrder;

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getStationOrder() {
		return stationOrder;
	}

	public void setStationOrder(int stationOrder) {
		this.stationOrder = stationOrder;
	}
	
}
