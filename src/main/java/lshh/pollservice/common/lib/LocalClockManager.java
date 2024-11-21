package lshh.pollservice.common.lib;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class LocalClockManager implements ClockManager {
    @Override
    public Clock getClock() {
        return Clock.fixed(Instant.now(), ZoneId.of("Asia/Seoul"));
    }
}
