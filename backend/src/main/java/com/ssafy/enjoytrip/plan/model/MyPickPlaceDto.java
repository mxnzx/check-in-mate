package com.ssafy.enjoytrip.plan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPickPlaceDto {
    //사용할 애들만 담는다
    private int pickPlaceNo;    //pk
    private String place_name;  //장소명
    private String place_url;   //카카오 장소url
    private String startTime;   //여행시작시간
    private String address_name;
    private String x;   //위도
    private String y;   //경도
    private int seq;    //순서
    private int dayNo;  //fk

}
