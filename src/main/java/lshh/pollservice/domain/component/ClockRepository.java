package lshh.pollservice.domain.component;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.lib.ClockManager;
import org.springframework.stereotype.Component;

import java.time.Clock;

@RequiredArgsConstructor
@Component
public class ClockRepository {
    private final ClockManager clockManager;

    public Clock getClock() {
        return clockManager.getClock();
    }
}
