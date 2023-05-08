package com.ssafy.enjoytrip.plan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlanBoardDto {
	int tripPlanId;
	String registerTime;
	String tripPlanTitle;
	String tripPlanStartDate;
	String tripPlanEndDate;
	String tripPlanDetail;
	String userId;

}
