package com.app.epolice.repository;

import com.app.epolice.model.entity.rooms.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * The interface Room repository.
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<Room> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all rooms by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_room where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<Room> findAllRoomsByDate(Date date);
}
