package com.ACMEFresh.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSessions {

	@Id
	private Integer customerId;
	
	private String uuid;
	
	private LocalDateTime date;
	
}
