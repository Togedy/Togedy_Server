package Togedy.server.Service.Planner;

import Togedy.server.Dto.FindByDateRequestDto;
import Togedy.server.Dto.Planner.Request.CreateStudyPlanRequestDto;
import Togedy.server.Dto.Planner.Response.ReadStudyPlansResponseDto;
import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyPlan;
import Togedy.server.Entity.StudyPlanner.StudyRecord;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import Togedy.server.Repository.PlannerRepository;
import Togedy.server.Repository.Planner.StudyPlanRepository;
import Togedy.server.Repository.Planner.StudyRecordRepository;
import Togedy.server.Repository.Planner.StudyTagRepository;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.PlannerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyPlanService {
    private final PlannerRepository plannerRepository;
    private StudyTagRepository studyTagRepository;
    private StudyPlanRepository studyPlanRepository;

    private StudyRecordRepository studyRecordRepository;

    @Transactional
    public Long save(Long userId, CreateStudyPlanRequestDto requestDto) {
        Planner planner = plannerRepository.findByUserId(userId)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        StudyTag studyTag = studyTagRepository.findById(requestDto.getStudyTagId())
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.TAG_NOT_EXIST));

        StudyPlan studyPlan = requestDto.toEntity(planner, studyTag);

        return studyPlanRepository.save(studyPlan).getId();
    }

    public List<ReadStudyPlansResponseDto> getStudyPlansByDate(Long userId, FindByDateRequestDto requestDto) {
        // 사용자 플래너 가져오기
        Planner planner = plannerRepository.findByUserId(userId)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        // 날짜에 맞는 studyPlan 리스트 조회
        List<StudyPlan> studyPlans = studyPlanRepository.findAllByPlannerAndDate(planner, requestDto.getDate());

        // 날짜에 맞는 studyRecord 리스트 조회
        List<StudyRecord> studyRecords = studyRecordRepository.findAllByDate(requestDto.getDate());

        // StudyRecord를 시간 리스트로 변환
        List<List<String>> recordTimes = studyRecords.stream()
                .map(record -> List.of(record.getStartTime().toString(), record.getEndTime().toString()))
                .toList();

        return studyPlans.stream()
                .map(plan -> ReadStudyPlansResponseDto.of(plan, recordTimes))
                .toList();
    }
}
