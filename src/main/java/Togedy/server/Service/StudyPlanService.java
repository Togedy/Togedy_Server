package Togedy.server.Service;

import Togedy.server.Dto.Planner.Request.CreateStudyPlanRequestDto;
import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyPlan;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import Togedy.server.Repository.PlannerRepository;
import Togedy.server.Repository.StudyPlanRepository;
import Togedy.server.Repository.StudyTagRepository;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.PlannerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyPlanService {
    private final PlannerRepository plannerRepository;
    private StudyTagRepository studyTagRepository;
    private StudyPlanRepository studyPlanRepository;

    @Transactional
    public Long save(Long userId, CreateStudyPlanRequestDto requestDto) {
        Planner planner = plannerRepository.findByUserId(userId)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        StudyTag studyTag = studyTagRepository.findById(requestDto.getStudyTagId())
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.STUDYTAG_NOT_EXIST));

        StudyPlan studyPlan = requestDto.toEntity(planner, studyTag);

        return studyPlanRepository.save(studyPlan).getId();
    }
}
