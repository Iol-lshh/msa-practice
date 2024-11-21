package lshh.pollservice.dto.participation;

public record VoteUpdateCommand(
    Long voteId,
    Long optionId
) {
}
