package com.guillaume.training.service;

import com.guillaume.training.exception.PerformanceNotFoundException;
import com.guillaume.training.repository.dao.PerformanceDAO;
import com.guillaume.training.service.model.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {
    private final PerformanceDAO performanceDAO;

    @Autowired
    PerformanceService(PerformanceDAO performanceDAO){
        this.performanceDAO = performanceDAO;
    }

    public List<Performance> findAll(){
        return performanceDAO.findAll();
    }

    public Performance findById(Long id){
        return performanceDAO.findById(id)
                .orElseThrow(() -> new PerformanceNotFoundException(id));
    }

    public Performance add(Performance performance){
        return performanceDAO.save(performance);
    }

    public Performance update(Performance newPerformance, Long id){
       return performanceDAO.upsert(newPerformance, id);
    }
}
