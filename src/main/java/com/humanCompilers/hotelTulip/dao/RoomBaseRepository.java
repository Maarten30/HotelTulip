package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface RoomBaseRepository<T extends Room> extends CrudRepository<T, UUID> {


}
