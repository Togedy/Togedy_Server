package Togedy.server.Service;

import Togedy.server.Dto.Calendar.Request.CreateCategoryRequestDto;
import Togedy.server.Dto.Calendar.Response.ReadCategoryResponseDto;
import Togedy.server.Entity.Calendar.Category;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.CategoryRepository;
import Togedy.server.Repository.UserRepository;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.CalendarException;
import Togedy.server.Util.Exception.Domain.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createCategory(Long userId, CreateCategoryRequestDto requestDto) {
        // 카테고리 이름 중복 검사
        if (categoryRepository.existsByName(requestDto.getName())) {
            throw new CalendarException(BaseResponseStatus.DUPLICATED_CATEGORYNAME);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Category category = requestDto.toEntity(user);

        return categoryRepository.save(category).getId();
    }

    // 사용자별 카테고리 조회
    public List<ReadCategoryResponseDto> getUserCategories(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        List<Category> categories = categoryRepository.findByUser(user);

        return categories.stream()
                .map(ReadCategoryResponseDto::of)
                .collect(Collectors.toList());
    }
}
