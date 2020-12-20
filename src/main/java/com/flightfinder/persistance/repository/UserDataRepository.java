package com.flightfinder.persistance.repository;

import com.flightfinder.persistance.model.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends CrudRepository<UserData, Long> {
    List<UserData> findByEmail(String email);

    long deleteByEmail(String email);

    long deleteByEmailAndOriginAndDestination(String email, String origin, String destination);

}