package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.DataByDayDto;
import com.ssafy.enjoytrip.plan.model.MyPickPlaceDto;
import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.PlanFileDto;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private SqlSession sqlSession;

    public PlanServiceImpl(PlanMapper planMapper) {
        super();
        this.planMapper = planMapper;
    }

    @Override
    public void registerFile(List<PlanFileDto> fileInfos) throws Exception {
        planMapper.registerFile(fileInfos);
    }

    @Override
    public void write(PlanDto planDto) throws Exception {
        System.out.println("<<<<< 나의 여행계획 서비스 들어옴 >>>>>>");

        System.out.println("서비스에 dto 들어옴>>>>>>>>>>>>"+planDto);
        int articleCnt = sqlSession.getMapper(PlanMapper.class).writeArticle(planDto);
        System.out.println(articleCnt);
        int articleNo = planDto.getArticleNo();

        for(int i=0; i < planDto.getDataByDay().size(); i++) {
            DataByDayDto dataByDayDto = planDto.getDataByDay().get(i);
            dataByDayDto.setArticleNo(articleNo);
            //서비스 다녀온다
            int byDayCnt = sqlSession.getMapper(PlanMapper.class).writeArticleByDay(dataByDayDto);
            int dayNo = dataByDayDto.getDayNo();
            System.out.println("dayNo: " + dataByDayDto.getDayNo());
            for (int j = 0; j < dataByDayDto.getMyPickPlace().size(); j++) {
                MyPickPlaceDto myPickPlaceDto = dataByDayDto.getMyPickPlace().get(j);
                myPickPlaceDto.setDayNo(dayNo);
                myPickPlaceDto.setSeq(j+1);

                int pickPlaceCnt = sqlSession.getMapper(PlanMapper.class).writeArticleByDayBySeq(myPickPlaceDto);

            }
        }
    }

    @Override
    public List<PlanDto> list(Map<String, String> map) throws SQLException {
        return null;
    }

    @Override
    public void delete(int planAritcleNo, String path) throws SQLException {

    }

    @Override
    public PlanDto viewPlanArticle(int articleNo) throws SQLException {
        // 1번 테이블
        PlanDto planDto = sqlSession.getMapper(PlanMapper.class).viewPlanArticle(articleNo);
        //2번 테이블
        planDto.setDataByDay(sqlSession.getMapper(PlanMapper.class).viewPlanArticleByDay(articleNo));
        //3번 테이블
        for (int i=0; i<planDto.getDataByDay().size();i++) {
            //article이 일치하고, day가 i+1인 애들을 뽑고싶어
            planDto.getDataByDay().get(i).setMyPickPlace(sqlSession.getMapper(PlanMapper.class).viewPlanArticleByDayBySeq(articleNo, i+1));
        }
        return planDto;
    }
}
