package lshh.pollservice.dto.user;

public record UserAddAccountCommandByGoogle(
    String googleId,
    Long userId
){
}
