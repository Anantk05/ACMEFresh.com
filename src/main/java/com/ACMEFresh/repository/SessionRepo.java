package com.ACMEFresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ACMEFresh.model.UserSessions;

@Repository
public interface SessionRepo extends JpaRepository<UserSessions, Integer>{

	public UserSessions findByUuid( String uuid );
}
