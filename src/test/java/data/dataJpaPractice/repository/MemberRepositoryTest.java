package data.dataJpaPractice.repository;

import data.dataJpaPractice.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@PersistenceContext
	private EntityManager em;

	private JpaEntityInformation<Member, ?> entityInformation;
	private static final Logger log = (Logger) LoggerFactory.getLogger(MemberRepositoryTest.class);
	private static final Member 홍길동 = new Member("홍길동");

	@BeforeEach
	void setUp() {
		entityInformation = JpaEntityInformationSupport.getEntityInformation(Member.class, em);
	}

	@DisplayName("저장_후_비교_한다")
	@Test
	void 저장_후_비교_한다() {
		log.info("홍길동이 비영속 상태 인가요? {}", entityInformation.isNew(홍길동));
		log.info("홍길동 ID = {}", 홍길동.getId());

		Member 결과 = memberRepository.save(홍길동);

		assertThat(결과).isEqualTo(홍길동);
	}

	@DisplayName("회원_찾기")
	@Test
	void find() {
		log.info("홍길동이 비영속 상태 인가요? {}", entityInformation.isNew(홍길동));
		log.info("홍길동 ID = {}", 홍길동.getId());

		Member 저장됨 = memberRepository.save(홍길동);
		Optional<Member> 결과 = memberRepository.findById(저장됨.getId());

		assertThat(결과).hasValue(홍길동);
	}
}