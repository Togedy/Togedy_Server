package Togedy.server.Service.Planner;

import Togedy.server.Dto.Planner.Request.CreateStudyGoalRequestDto;
import Togedy.server.Dto.Planner.Response.CreateStudyGoalResponseDto;
import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyGoal;
import Togedy.server.Repository.PlannerRepository;
import Togedy.server.Repository.Planner.StudyGoalRepository;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.PlannerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyGoalService {

    private final PlannerRepository plannerRepository;
    private final StudyGoalRepository studyGoalRepository;

    @Transactional
    public CreateStudyGoalResponseDto setStudyGoal(Long userId, CreateStudyGoalRequestDto requestDto) {

        // 사용자 플래너 조회
        Planner planner = plannerRepository.findByUserId(userId)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        // 이미 존재하는 목표 조회
        StudyGoal existingGoal = studyGoalRepository.findByPlannerAndDate(planner, requestDto.getDate())
                .orElse(null);

        LocalTime existingActualTime = existingGoal != null ? existingGoal.getActualTime() : LocalTime.of(0,0);

        if (existingGoal != null) {
            // 기존 목표가 존재하면 목표를 갱신
            existingGoal.updateTargetTime(requestDto.getTargetTime());
        } else {
            // 새로운 목표 생성
            StudyGoal studyGoal = requestDto.toEntity(planner, existingActualTime);
            studyGoalRepository.save(studyGoal);
        }

        // 최종적으로 저장된 목표를 반환
        StudyGoal savedGoal = studyGoalRepository.findByPlannerAndDate(planner, requestDto.getDate())
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.GOAL_NOT_EXIST));

        return CreateStudyGoalResponseDto.of(savedGoal);
    }
}
