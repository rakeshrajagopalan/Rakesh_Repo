package com.metroscs.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.metroscs.data.Card;
import com.metroscs.data.Station;
import com.metroscs.data.Travel;

@Repository
public class TravelDAOImpl extends AbstractDao implements TravelDAO {

	public void flagTravelStart(Travel travel) {
		getSession().save(travel);
	}

	@Cacheable(value="travelCache", key="#travel")
	public void flagTravelEnd(Travel travel) {
		String sql = "Select Count(StationId)-1 From Station Where StationId Between :fromStation And :toStation";
		Query query = getSession().createQuery(sql);
		if (travel.getToStation() <= travel.getFromStation()) {
			query.setInteger("fromStation", travel.getToStation());
			query.setInteger("toStation", travel.getFromStation());
		} else {
			query.setInteger("fromStation", travel.getFromStation());
			query.setInteger("toStation", travel.getToStation());
		}
		long stationCount = (Long) query.list().get(0);
		float charges = travel.getBaseCharge() * stationCount;
		travel.setTravelCharges(charges);
		getSession().update(travel);
	}

	public Travel getUncompletedTravel(Card card) {
		Criteria criteria = getSession().createCriteria(Travel.class);
		criteria.add(Restrictions.eq("cardId", card.getCardId()));
		criteria.add(Restrictions.eq("travelCharges", 0.00f));
		return (Travel) criteria.uniqueResult();
	}
	
	public List getCompletedTravelByCard(Card card) {
		Criteria criteria = getSession().createCriteria(Travel.class);
		criteria.add(Restrictions.eq("cardId", card.getCardId()));
		return criteria.list();
	}

	public int totalFootfallPerStation(Station station) {
		String sql = "Select Sum(FromStation) + Sum(ToStation) From Travel Where FromStation = :fromStation Or ToStation = :toStation";
		Query query = getSession().createQuery(sql);
		query.setInteger("fromStation", station.getStationId());
		query.setInteger("toStation", station.getStationId());
		long count = (Long) query.list().get(0);
		return (int) count;
	}

}
