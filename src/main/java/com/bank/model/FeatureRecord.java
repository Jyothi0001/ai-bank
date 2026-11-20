package com.bank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "feature_records")
public class FeatureRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long userId;
	String featureType, title, referenceValue, status;
	BigDecimal amount;
	@Column(length = 2000)
	String details;
	LocalDateTime createdAt = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long v) {
		userId = v;
	}

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String v) {
		featureType = v;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String v) {
		title = v;
	}

	public String getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(String v) {
		referenceValue = v;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String v) {
		status = v;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal v) {
		amount = v;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String v) {
		details = v;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
