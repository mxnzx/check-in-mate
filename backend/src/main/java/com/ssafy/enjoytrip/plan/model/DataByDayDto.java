package com.ssafy.enjoytrip.plan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DataByDayDto {
    private int dayNo;  //pk
    private String dayIdx;  //차수
    private String title;   //day별 제목
    private String content; //day별 내용
    private int articleNo;  //fk
    private List<MyPickPlaceDto> myPickPlace;   //day별 여행지 목록

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }


}
