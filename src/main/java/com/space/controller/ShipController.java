package com.space.controller;

import com.space.dto.UrlParams;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// добавить в конце @RequestMapping(value = "/rest")
public class ShipController {

    private ShipService shipService;

    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }
    /*@GetMapping(value = "rest/ships")*/
    @RequestMapping(value = "rest/ships", method = RequestMethod.GET)
    public List<Ship> read(@RequestParam (value = "name", required = false) String name,
                               @RequestParam (value = "planet", required = false) String planet,
                               @RequestParam (value = "shipType", required = false) ShipType shipType,
                               @RequestParam (value = "after", required = false) Long/*Date*/ after,
                               @RequestParam (value = "before", required = false) Long/*Date*/ before,
                               @RequestParam (value = "isUsed", required = false) Boolean isUsed,
                               @RequestParam (value = "minSpeed", required = false) Double minSpeed,
                               @RequestParam (value = "maxSpeed", required = false) Double maxSpeed,
                               @RequestParam (value = "minCrewSize", required = false) Integer minCrewSize,
                               @RequestParam (value = "maxCrewSize", required = false) Integer maxCrewSize,
                               @RequestParam (value = "minRating", required = false) Double minRating,
                               @RequestParam (value = "maxRating", required = false) Double maxRating,
                               @RequestParam (value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                               @RequestParam (value = "pageSize", required = false, defaultValue = "3") Integer pageSize,
                               @RequestParam (value = "order", required = false, defaultValue = "ID") ShipOrder order) {
        UrlParams urlParams = new UrlParams(name,planet,shipType,after,before,isUsed,minSpeed,maxSpeed,
                minCrewSize,maxCrewSize,minRating,maxRating,pageNumber,pageSize,order);
        PagedListHolder<Ship> list = new PagedListHolder<>(shipService.read(urlParams),
                new MutableSortDefinition(order.getFieldName(), false, true));
        list.resort();
        list.setPageSize(pageSize);
        list.setPage(pageNumber);
        return list.getPageList();
    }

    @RequestMapping(value = "rest/ships/count", method = RequestMethod.GET)
    public Integer count(@RequestParam (value = "name", required = false) String name,
                               @RequestParam (value = "planet", required = false) String planet,
                               @RequestParam (value = "shipType", required = false) ShipType shipType,
                               @RequestParam (value = "after", required = false) Long after,
                               @RequestParam (value = "before", required = false) Long before,
                               @RequestParam (value = "isUsed", required = false) Boolean isUsed,
                               @RequestParam (value = "minSpeed", required = false) Double minSpeed,
                               @RequestParam (value = "maxSpeed", required = false) Double maxSpeed,
                               @RequestParam (value = "minCrewSize", required = false) Integer minCrewSize,
                               @RequestParam (value = "maxCrewSize", required = false) Integer maxCrewSize,
                               @RequestParam (value = "minRating", required = false) Double minRating,
                               @RequestParam (value = "maxRating", required = false) Double maxRating) {
        UrlParams urlParams = new UrlParams(name,planet,shipType,after,before,isUsed,minSpeed,maxSpeed,
                minCrewSize,maxCrewSize,minRating,maxRating);
        return shipService.count(urlParams);
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.POST)
    public ResponseEntity<Ship> add(@RequestBody Ship ship) {
        return shipService.add(ship);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ship> get(@PathVariable("id") String id) {
        return shipService.get(id);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.POST)
    public ResponseEntity<Ship> update(@PathVariable("id") String id, @RequestBody Ship ship) {
        return shipService.update(id, ship);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ship> delete(@PathVariable("id") String id) {
        return shipService.delete(id);
    }
}
