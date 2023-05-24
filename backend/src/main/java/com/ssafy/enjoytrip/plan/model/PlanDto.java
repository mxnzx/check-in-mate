package com.ssafy.enjoytrip.plan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PlanDto {
    private int articleNo;
    private String registerTime;
    private String mainTitle;
    private String userid;
    private int hit;
    private String img;
    private String mainContent;
    private List<DataByDayDto> dataByDay;
}
