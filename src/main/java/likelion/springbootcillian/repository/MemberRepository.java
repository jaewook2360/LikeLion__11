package likelion.springbootcillian.repository;

import likelion.springbootcillian.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
