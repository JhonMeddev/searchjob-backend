package com.searchjob.repository;

import com.searchjob.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    public Optional<UserModel> findById(long id);
    public Optional<UserModel> findByEmail(String email);
}
