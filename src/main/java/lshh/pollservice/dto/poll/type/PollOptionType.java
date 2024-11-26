package lshh.pollservice.dto.poll.type;

public interface PollOptionType {
    static PollOptionType valueOf(String s) {
        return SelectPollOptionType.valueOf(s);
    }

    String name();
}
