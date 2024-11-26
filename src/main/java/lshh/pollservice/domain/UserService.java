package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.exception.PersistenceDuplicatedException;
import lshh.pollservice.domain.component.user.UserFactory;
import lshh.pollservice.domain.component.user.UserRepository;
import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.dto.user.UserSignUpCommand;
import lshh.pollservice.dto.user.UserSimple;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final UserFactory factory;

    public UserSimple signUp(UserSignUpCommand command) {
        repository.findByLoginId(command.loginId())
                .ifPresent(user -> {throw new PersistenceDuplicatedException("User already exists");});
        User user = factory.generate(command);
        repository.save(user);
        return UserSimple.from(user);
    }
}
