package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.exception.PersistenceDuplicatedException;
import lshh.pollservice.domain.component.user.UserMemberFactory;
import lshh.pollservice.domain.component.user.UserMemberRepository;
import lshh.pollservice.domain.entity.user.UserMember;
import lshh.pollservice.dto.user.UserDetail;
import lshh.pollservice.dto.user.UserRoleUpdate;
import lshh.pollservice.dto.user.UserSignUp;
import lshh.pollservice.dto.user.UserSimple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMemberRepository repository;
    private final UserMemberFactory factory;

    @Transactional
    public UserSimple signUp(UserSignUp command) {
        repository.findByLoginId(command.loginId())
                .ifPresent(user -> {throw new PersistenceDuplicatedException("UserMember already exists");});
        UserMember userMember = factory.generate(command);
        repository.save(userMember);
        return UserSimple.from(userMember);
    }

    @Transactional
    public UserDetail addRole(UserRoleUpdate command) {
        UserMember userMember = repository.getByLoginId(command.loginId());
        userMember.addRole(command.role());
        repository.save(userMember);
        return UserDetail.from(userMember);
    }

    @Transactional(readOnly = true)
    public UserDetail detail(String loginId) {
        UserMember userMember = repository.getByLoginId(loginId);
        return UserDetail.from(userMember);
    }
}
