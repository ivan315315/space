package com.space.repository;

import com.space.dto.UrlParams;
import com.space.model.Ship;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ShipSelectImpl implements ShipSelect {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Ship> listShip(UrlParams urlParams) {
        return em.createQuery(getQlScript(urlParams), Ship.class).getResultList();
    }

    private String getQlScript(UrlParams urlParams) {
        String qlScript = "from Ship where 1=1";
        if (urlParams.getName() != null) {
            qlScript = qlScript + " and name like '%" + urlParams.getName() + "%'";
        }
        if (urlParams.getPlanet() != null) {
            qlScript = qlScript + " and planet like '%" + urlParams.getPlanet() + "%'";
        }
        if (urlParams.getShipType() != null) {
            qlScript = qlScript + " and shipType like '%" + urlParams.getShipType() + "%'";
        }
        if (urlParams.getUsed() != null) {
            qlScript = qlScript + " and isUsed = " + urlParams.getUsed();
        }
        if (urlParams.getMinSpeed() != null) {
            qlScript = qlScript + " and speed >= " + urlParams.getMinSpeed();
        }
        if (urlParams.getMaxSpeed() != null) {
            qlScript = qlScript + " and speed <= " + urlParams.getMaxSpeed();
        }
        if (urlParams.getMinCrewSize() != null) {
            qlScript = qlScript + " and crewSize >= " + urlParams.getMinCrewSize();
        }
        if (urlParams.getMaxCrewSize() != null) {
            qlScript = qlScript + " and crewSize <= " + urlParams.getMaxCrewSize();
        }
        if (urlParams.getMinRating() != null) {
            qlScript = qlScript + " and rating >= " + urlParams.getMinRating();
        }
        if (urlParams.getMaxRating() != null) {
            qlScript = qlScript + " and rating <= " + urlParams.getMaxRating();
        }
        if (urlParams.getOrder() != null) {
            qlScript = qlScript + " order by " + urlParams.getOrder();
        }
        return qlScript;
    }
}