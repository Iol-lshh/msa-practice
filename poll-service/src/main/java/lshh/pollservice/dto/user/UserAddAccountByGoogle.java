package lshh.pollservice.dto.user;

public record UserAddAccountByGoogle(
    String googleId,
    Long userId
){
}
