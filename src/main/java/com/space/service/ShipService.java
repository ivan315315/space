package com.space.service;

import com.space.dto.UrlParams;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Transactional
    public List<Ship> read(UrlParams urlParams) {
        if (urlParams.getMinSpeed() != null){
            urlParams.setMinSpeed(getSpeed(urlParams.getMinSpeed()));
        }
        if (urlParams.getMaxSpeed() != null){
            urlParams.setMaxSpeed(getSpeed(urlParams.getMaxSpeed()));
        }
        List<Ship> ships = shipRepository.listShip(urlParams);
        ships.removeIf(ship -> (urlParams.getBefore() != null && !ship.getProdDate().before(urlParams.getBefore())) ||
                (urlParams.getAfter() != null && !ship.getProdDate().after(urlParams.getAfter())));
        return ships;
    }

    @Transactional
    public Integer count(UrlParams urlParams) {
        return read(urlParams).size();
    }

    @Transactional
    public ResponseEntity<Ship> add(Ship ship) {
        ResponseEntity<Ship> responseEntity = checkShip(ship);
        if (responseEntity != null) {
            return responseEntity;
        }
        ship.setSpeed(getSpeed(ship.getSpeed()));
        if (ship.getUsed() == null) {
            ship.setUsed(false);
        }
        ship.setRating(countCoef(ship));
        return new ResponseEntity<>(shipRepository.save(ship), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Ship> update(String id, Ship ship){
        ResponseEntity<Ship> responseEntity = checkId(id);
        if (responseEntity != null) {
            return responseEntity;
        }
        Long idLong = Long.parseLong(id);

        ship.setId(idLong);
        Ship shipFromBd = shipRepository.findById(idLong).get();
        if (ship.getName() == null) {
            ship.setName(shipFromBd.getName());
        }
        if (ship.getPlanet() == null) {
            ship.setPlanet(shipFromBd.getPlanet());
        }
        if (ship.getShipType() == null) {
            ship.setShipType(shipFromBd.getShipType());
        }
        if (ship.getProdDate() == null) {
            ship.setProdDate(shipFromBd.getProdDate());
        }
        if (ship.getUsed() == null) {
            ship.setUsed(shipFromBd.getUsed());
        }
        if (ship.getSpeed() == null) {
            ship.setSpeed(shipFromBd.getSpeed());
        } else {
            ship.setSpeed(getSpeed(ship.getSpeed()));
        }
        if (ship.getCrewSize() == null) {
            ship.setCrewSize(shipFromBd.getCrewSize());
        }
        if ((responseEntity = checkShip(ship)) != null) {
            return responseEntity;
        }
        ship.setRating(countCoef(ship));
        return new ResponseEntity<>(shipRepository.save(ship), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Ship> get(String id){
        Long idLong;
        ResponseEntity<Ship> responseEntity = checkId(id);
        if (responseEntity != null) {
            return responseEntity;
        }
        idLong = Long.parseLong(id);
        return new ResponseEntity<>(shipRepository.findById(idLong).get(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Ship> delete(String id){
        Long idLong;
        ResponseEntity<Ship> responseEntity = checkId(id);
        if (responseEntity != null) {
            return responseEntity;
        }
        idLong = Long.parseLong(id);
        shipRepository.deleteById(idLong);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


//////////////////////////help_metods/////////////////////////////////////////

    public ResponseEntity<Ship> checkId(String id) {
        Long idLong;
        try {
            idLong = Long.parseLong(id);
            if (idLong <= 0 || (idLong - (long) idLong) > 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (shipRepository.existsById(idLong) == false) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return null;
    }

    private ResponseEntity<Ship> checkShip(Ship ship){
        if (ship.getName() == null || ship.getName().length() == 0 || ship.getName().length() > 50) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (ship.getPlanet() == null || ship.getPlanet().length() == 0 || ship.getPlanet().length() > 50) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (ship.getShipType() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (ship.getProdDate() == null || getYearC(ship.getProdDate().getTime()) < 2800 || 3019 < getYearC(ship.getProdDate().getTime())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (ship.getSpeed() == null || ship.getSpeed() < 0.01d || 0.99d < ship.getSpeed()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (ship.getCrewSize() == null || ship.getCrewSize() < 1 || 9999 < ship.getCrewSize()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private double countCoef(Ship ship){
        double rating;
        double v = ship.getSpeed();
        double k = ship.getUsed() == false ? 1: 0.5d;
        int y0 = 3019;
        int y1 = getYearC(ship.getProdDate().getTime());
        rating = (80 * v * k) / ((y0 - (y1) + 1));
        BigDecimal bd = new BigDecimal(Double.toString(rating));
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private int getYearC(Long prodDate){
        Calendar calendarThis = Calendar.getInstance();
        calendarThis.setTimeInMillis(prodDate);
        return calendarThis.get(Calendar.YEAR);
    }

    private Double getSpeed(Double speed){
        BigDecimal bd = new BigDecimal(Double.toString(speed));
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
