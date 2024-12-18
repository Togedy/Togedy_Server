package Togedy.server.Service.Planner;

import Togedy.server.Dto.Planner.Request.CreateStudyGoalRequestDto;
import Togedy.server.Dto.Planner.Response.ReadStudyGoalResponseDto;
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
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyGoalService {

    private final PlannerRepository plannerRepository;
    private final StudyGoalRepository studyGoalRepository;

    // 목표 공부량 설정
    @Transactional
    public Long setStudyGoal(Long userId, CreateStudyGoalRequestDto requestDto) {

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

        return savedGoal.getId();
    }

    // 목표 공부량 수정
    @Transactional
    public Long updateStudyGoal(Long userId, Long goalId, CreateStudyGoalRequestDto requestDto) {
        Planner planner = plannerRepository.findByUserId(userId)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        StudyGoal studyGoal = studyGoalRepository.findByIdAndPlanner(goalId, planner)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.GOAL_NOT_EXIST));

        studyGoal.updateTargetTime(requestDto.getTargetTime());

        return studyGoal.getId();
    }

    // 목표 공부량 조회
    public ReadStudyGoalResponseDto getStudyGoal(Long userId, LocalDate date) {
        Planner planner = plannerRepository.findByUserId(userId)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        StudyGoal studyGoal = studyGoalRepository.findByPlannerAndDate(planner, date)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.GOAL_NOT_EXIST));

        return ReadStudyGoalResponseDto.of(studyGoal);
    }
}
