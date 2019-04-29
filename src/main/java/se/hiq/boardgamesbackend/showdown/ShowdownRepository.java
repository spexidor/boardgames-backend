package se.hiq.boardgamesbackend.showdown;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowdownRepository extends CrudRepository<Showdown, Long> {

    //@Query("Select new se.hiq.boardgamesbackend.showdown.Showdown from se.hiq.boardgamesbackend.showdown.Showdown ORDER BY id DESC LIMIT 1")

    @Query(
            value = "SELECT * FROM showdown ORDER BY id DESC LIMIT 1",
            nativeQuery = true)
    Showdown findLatest();
}