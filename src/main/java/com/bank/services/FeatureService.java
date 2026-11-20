//package com.bank.services;
//
//import org.springframework.stereotype.Service;
//
//import com.bank.model.FeatureRecord;
//import com.bank.repo.FeatureRecordRepository;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//@Service
//public class FeatureService {
//	final FeatureRecordRepository r;
//
//	public FeatureService(FeatureRecordRepository r) {
//		this.r = r;
//	}
//
//	public FeatureRecord save(Long uid, String type, String title, String ref, String status, BigDecimal amount,
//			String details) {
//		FeatureRecord f = new FeatureRecord();
//		f.setUserId(uid);
//		f.setFeatureType(type);
//		f.setTitle(title);
//		f.setReferenceValue(ref);
//		f.setStatus(status);
//		f.setAmount(amount);
//		f.setDetails(details);
//		return r.save(f);
//	}
//
//	public List<FeatureRecord> user(Long id) {
//		return r.findByUserIdOrderByCreatedAtDesc(id);
//	}
//
//	public List<FeatureRecord> type(String t) {
//		return r.findByFeatureTypeOrderByCreatedAtDesc(t);
//	}
//}
