package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.AreaDao;
import com.humanCompilers.hotelTulip.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AreaService {

    private final AreaDao areaDao;

    @Autowired
    public AreaService(@Qualifier("fakeAreaDao") AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    public int addArea(Area area) {
        return areaDao.insertArea(area);
    }

    public List<Area> getAllAreas() {
        return areaDao.selectAllAreas();
    }

    public Optional<Area> getAreaById(UUID id) {
        return areaDao.selectAreaById(id);
    }

    public int deleteAreaById(UUID id) {
        return areaDao.deleteAreaById(id);
    }

    public int updateAreaById(UUID id, Area newArea) {
        return areaDao.updateAreaById(id, newArea);
    }
}
