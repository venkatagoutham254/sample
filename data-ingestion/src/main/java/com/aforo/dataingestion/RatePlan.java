package com.aforo.dataingestion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="RatePlan")
public class RatePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rate_plan_id;
    private String rate_plan_name;
    private String description;

    
    private String pricing_model;

    
    private int base_fee;
    private int tier_min;
    private int tier_max;
    private int tier_rate;
    private int usage_fee;
    private String currency;

    private String start_date;
    private String end_date;

 
    private String status;
    
    int getRate_plan_id() {
		return rate_plan_id;
	}

	public void setRate_plan_id(int rate_plan_id) {
		this.rate_plan_id = rate_plan_id;
	}

	public String getRate_plan_name() {
		return rate_plan_name;
	}

	public void setRate_plan_name(String rate_plan_name) {
		this.rate_plan_name = rate_plan_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPricing_model() {
		return pricing_model;
	}

	public void setPricing_model(String pricing_model) {
		this.pricing_model = pricing_model;
	}

	public int getBase_fee() {
		return base_fee;
	}

	public void setBase_fee(int base_fee) {
		this.base_fee = base_fee;
	}

	public int getTier_min() {
		return tier_min;
	}

	public void setTier_min(int tier_min) {
		this.tier_min = tier_min;
	}

	public int getTier_max() {
		return tier_max;
	}

	public void setTier_max(int tier_max) {
		this.tier_max = tier_max;
	}

	public int getTier_rate() {
		return tier_rate;
	}

	public void setTier_rate(int tier_rate) {
		this.tier_rate = tier_rate;
	}

	public int getUsage_fee() {
		return usage_fee;
	}

	public void setUsage_fee(int usage_fee) {
		this.usage_fee = usage_fee;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
