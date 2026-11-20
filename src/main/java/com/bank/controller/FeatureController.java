//package com.bank.controller;
//
//import org.springframework.web.bind.annotation.*;
//
//import com.bank.model.FeatureRecord;
//import com.bank.services.FeatureService;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//@RestController
//@RequestMapping("/api/features")
//public class FeatureController {
//	final FeatureService s;
//
//	public FeatureController(FeatureService s) {
//		this.s = s;
//	}
//
//	@GetMapping("/{uid}")
//	public List<FeatureRecord> user(@PathVariable Long uid) {
//		return s.user(uid);
//	}
//
//	@PostMapping("/{uid}")
//	public FeatureRecord create(@PathVariable Long uid, @RequestBody Map<String, Object> p) {
//		Object a = p.get("amount");
//		return s.save(uid, String.valueOf(p.getOrDefault("featureType", "GENERAL")),
//				String.valueOf(p.getOrDefault("title", "")), String.valueOf(p.getOrDefault("referenceValue", "")),
//				String.valueOf(p.getOrDefault("status", "SUCCESS")),
//				a == null ? BigDecimal.ZERO : new BigDecimal(a.toString()),
//				String.valueOf(p.getOrDefault("details", "")));
//	}
//
//	@GetMapping("/admin/{type}")
//	public List<FeatureRecord> type(@PathVariable String type) {
//		return s.type(type);
//	}
//}
