package lshh.pollservice.dto.poll;

public interface PollType {
    static PollType valueOf(String s) {
        return SelectPollType.valueOf(s);
    }

    String name();
}
