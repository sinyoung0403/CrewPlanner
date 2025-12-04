package com.reactionservice.crewproject.infra.jpa;

import java.util.Optional;

import com.reactionservice.crewproject.domain.User;
import com.reactionservice.crewproject.infra.jpa.common.BaseRepository;

public interface UserRepository extends BaseRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
