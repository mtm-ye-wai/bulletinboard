package com.mtm.bulletinboard.persistance.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mtm.bulletinboard.common.enums.Status;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@ToString(exclude = { "updatedAt", "deletedAt" })
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private String description;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@Column(name = "created_user_id")
	private Integer createdUserId;

	@ManyToOne
	@JoinColumn(name = "created_user_id", insertable = false, updatable = false)
	private User createdUser;

	@Column(name = "updated_user_id")
	private Integer updatedUserId;

	@ManyToOne
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	private User updatedUser;

	@Column(name = "deleted_user_id")
	private Integer deletedUserId;

	@ManyToOne
	@JoinColumn(name = "deleted_user_id", insertable = false, updatable = false)
	@Nullable
	private User deletedUser;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
}
