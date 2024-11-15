package Togedy.server.Security;

import Togedy.server.Entity.User.User;
import Togedy.server.Repository.UserRepository;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByNickname(username).orElseThrow(() ->
                new UserException(BaseResponseStatus.USER_NOT_EXIST));


        return AuthMember.builder()
                .id(user.getId())
                .username(user.getNickname())
                .password(user.getEmail())
                .build();
    }
}
