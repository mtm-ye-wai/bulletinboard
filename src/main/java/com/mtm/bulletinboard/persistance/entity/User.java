package com.mtm.bulletinboard.persistance.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mtm.bulletinboard.common.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString(exclude = { "password", "createdAt", "updatedAt", "deletedAt" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String profile;
	
	@Enumerated(EnumType.ORDINAL)
	private Role type;
	
	private String phone;
	
	private String address;
	
	private LocalDate dob;
	
	@Column(name = "created_user_id")
	private Integer createdUserId;
	
	@Column(name = "updated_user_id")
	private Integer updatedUserId;
	
	@Column(name = "deleted_user_id")
	private Integer deletedUserId;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

}
