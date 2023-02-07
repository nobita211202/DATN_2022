package org.datn.service.Impl;

import lombok.extern.log4j.Log4j2;
import org.datn.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Service
@Log4j2
public class StatisticalService {
    @Autowired
    OrderDao orderDao;

    public List<Map<String,String>> getByDate(String from, String to){
        log.info("log date :",from,to);
        return orderDao.getByDate(from,to);
    }

    public  Object getCoutnAll(){
        return new Object[]{orderDao.getCountUserAll(),orderDao.getSumMoneyAll(),orderDao.getCountCmonmentAll()};
    };

    public  Object getCountByDate(String from, String to){
        return new Object[]{
                orderDao.getCountUserByDate(from, to),
                orderDao.getSumMoneyByDate(from, to),
                orderDao.getCountCmonmentByDate(from, to)};
    };


}
