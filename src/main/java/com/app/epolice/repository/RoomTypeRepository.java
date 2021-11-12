package com.app.epolice.repository;

import com.app.epolice.model.entity.rooms.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType,Long> {
    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<RoomType> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all room types by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_room_type where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<RoomType> findAllRoomTypesByDate(Date date);
}
