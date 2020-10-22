package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Area;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeAreaDao")
public class FakeAreaDataAccessService implements AreaDao {

    private static List<Area> DB = new ArrayList<>();

    @Override
    public int insertArea(UUID id, Area area) {
        DB.add(new Area(id, area.getCapacity(), area.getPrice(), area.getType()));
        return 1;
    }

    @Override
    public List<Area> selectAllAreas() {
        return DB;
    }

    @Override
    public Optional<Area> selectAreaById(UUID id) {
        return DB.stream()
                .filter(area -> area.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteAreaById(UUID id) {
        Optional<Area> areaMaybe = selectAreaById(id);

        if (!(areaMaybe.isPresent())){
            return 0;
        }
        DB.remove(areaMaybe.get());
        return 1;
    }

    @Override
    public int updateAreaById(UUID id, Area updateArea) {
        return selectAreaById(id)
                .map(area -> {
                    int indexOfAreaToUpdate = DB.indexOf(area);
                    if(indexOfAreaToUpdate >= 0) {
                        DB.set(indexOfAreaToUpdate, new Area(id, updateArea.getCapacity(), updateArea.getPrice(), updateArea.getType()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
