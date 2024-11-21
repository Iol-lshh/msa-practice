package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
}
