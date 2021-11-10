package com.github.virginonline.planetsandlords.repository;

import com.github.virginonline.planetsandlords.entity.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  Repository interface for {@link Lord} class
 */
@Repository
public interface LordRepository extends JpaRepository<Lord,Long> {
    /**
     * SQL Query.
     * <p>
     * SELECT *
     * <br>
     * FROM {@link Lord}
     * <br>
     * ORDERED BY age DESC
     * <br>
     * LIMIT 0, 10
     * </p>
     *
     * @return {@link List} of top 10 {@link Lord} in ascending order of age.
     */
    List<Lord> findTop10ByOrderByAge();

    @Query("SELECT DISTINCT lord FROM Lord lord "
            + "LEFT JOIN Planet planet ON (lord.id = planet.lord.id) WHERE planet.lord IS NULL")
    List<Lord> findAllLoafers();
}
