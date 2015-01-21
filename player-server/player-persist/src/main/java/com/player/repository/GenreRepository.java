package com.player.repository;

import com.player.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Николай
 */
@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

    Genre findByName(String name);
}
