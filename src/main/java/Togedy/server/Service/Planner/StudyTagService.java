package Togedy.server.Service.Planner;

import Togedy.server.Dto.Planner.Request.CreateStudyTagRequestDto;
import Togedy.server.Dto.Planner.Response.ReadStudyTagResponseDto;
import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.PlannerRepository;
import Togedy.server.Repository.Planner.StudyTagRepository;
import Togedy.server.Repository.UserRepository;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.PlannerException;
import Togedy.server.Util.Exception.Domain.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyTagService {

    private final StudyTagRepository studyTagRepository;
    private final UserRepository userRepository;
    private final PlannerRepository plannerRepository;

    // 스터디 태그 생성
    @Transactional
    public Long createStudyTag(Long userId, CreateStudyTagRequestDto requestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Planner planner = plannerRepository.findByUser(user)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        // 스터디 태그 이름 중복 검사
        if (studyTagRepository.existsByNameAndPlanner(requestDto.getName(), planner)) {
            throw new PlannerException(BaseResponseStatus.DUPLICATED_TAG_NAME);
        }

        StudyTag studyTag = requestDto.toEntity(planner);

        return studyTagRepository.save(studyTag).getId();
    }

    // 스터디 태그 조회
    public List<ReadStudyTagResponseDto> getStudyTags(Long userId) {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        // 사용자 플래너 조회
        Planner planner = plannerRepository.findByUser(user)
                .orElseThrow(() -> new PlannerException(BaseResponseStatus.PLANNER_NOT_EXIST));

        // 플래너에 연결된 스터디 태그 조회
        List<StudyTag> studyTags = studyTagRepository.findByPlanner(planner);

        // 스터디 태그를 DTO로 변환
        return studyTags.stream()
                .map(ReadStudyTagResponseDto::of)
                .toList();
    }
}
