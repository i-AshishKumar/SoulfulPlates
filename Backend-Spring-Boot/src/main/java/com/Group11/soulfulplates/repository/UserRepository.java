package com.Group11.soulfulplates.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Group11.soulfulplates.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Optional<User> findByEmail(String email);
  Optional<User> findById(Long id);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  boolean existsById(Long id);


}
