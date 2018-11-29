package com.lesson6.hw.service;


import com.lesson6.hw.dao.PassengerDao;
import com.lesson6.hw.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Transactional
public class PassengerService extends GeneralService<Passenger> {
    private PassengerDao passengerDao;

    @Autowired
    public PassengerService(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }


    @Override
    public Passenger get(Long id) throws Exception {
        return passengerDao.findById(id);
    }

    @Override
    public void save(Passenger passenger) throws Exception {
        passengerDao.save(passenger);

    }

    @Override
    public Passenger update(Passenger passenger) {
        return passengerDao.update(passenger);
    }

    @Override
    public void delete(Long id) {
        passengerDao.delete(id);

    }

    public ArrayList<Passenger> regularPassengers() {
       return  passengerDao.regularPassengers();
    }
}
