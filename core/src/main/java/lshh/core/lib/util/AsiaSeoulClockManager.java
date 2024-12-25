package lshh.core.lib.util;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class AsiaSeoulClockManager implements ClockManager {
    @Override
    public Clock getClock() {
        return Clock.fixed(Instant.now(), ZoneId.of("Asia/Seoul"));
    }
}
