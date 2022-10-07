package data.dataJpaPractice.repository;

import data.dataJpaPractice.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
