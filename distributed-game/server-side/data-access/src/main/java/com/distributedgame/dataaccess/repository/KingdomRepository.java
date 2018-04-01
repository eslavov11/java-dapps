package com.distributedgame.dataaccess.repository;

import com.distributedgame.dataaccess.entity.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {
    List<Kingdom> findAllByUserId(long userId);

    @Query("select a from Kingdom as a where a.XCoord = ?1 and a.YCoord = ?2")
    Optional<Kingdom> findByXCoordAndYCoord(int xCoord, int yCoord);
}
