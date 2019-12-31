package com.space.repository;

import com.space.controller.ShipOrder;
import com.space.dto.UrlParams;
import com.space.model.Ship;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;


public interface ShipSelect<T> {

    List<T> listShip(UrlParams urlParams);
}