package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Area;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AreaDao {

    int insertArea(UUID id, Area area);

    default int insertArea(Area area) {
        UUID id = UUID.randomUUID();
        return insertArea(id, area);
    }

    List<Area> selectAllAreas();

    Optional<Area> selectAreaById(UUID id);

    int deleteAreaById(UUID id);

    int updateAreaById(UUID id, Area area);
}
