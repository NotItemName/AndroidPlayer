package com.player.repository;

import com.player.dto.GenreDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Николай
 */
@Repository
public interface GenreRepository extends CrudRepository<GenreDto, Integer> {

    GenreDto findByName(String name);
}
