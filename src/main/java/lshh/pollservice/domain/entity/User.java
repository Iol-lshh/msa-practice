package lshh.pollservice.domain.entity;

import jakarta.persistence.*;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String loginId;
    String name;
}
