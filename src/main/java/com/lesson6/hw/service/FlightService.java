package com.lesson6.hw.service;

import com.lesson6.hw.dao.FlightDao;
import com.lesson6.hw.models.Filter;
import com.lesson6.hw.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;

@Service
@Transactional
public class FlightService extends GeneralService<Flight> {

    private FlightDao flightDao;

    @Autowired
    public FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public Flight get(Long id) throws NullPointerException {
        try {
            return flightDao.findById(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("something went wrong: flight with id " + id + " not found");
        }

    }

    @Override
    public void save(Flight flight) throws Exception {
        flightDao.save(flight);

    }

    @Override
    public Flight update(Flight flight) {
        return flightDao.update(flight);
    }

    @Override
    public void delete(Long id) {
        flightDao.delete(id);

    }


    public HashSet<Flight> filterFlights(Filter filter) {
        return flightDao.flightsByDate(filter);


    }

}
