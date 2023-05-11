package com.ssafy.enjoytrip.plan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlanDetailDto {
	int tripPlanId;
	int contentId;
	String comment;
	int seq;
	String tripPlanStartDate;
	String tripPlanEndDate;
}
