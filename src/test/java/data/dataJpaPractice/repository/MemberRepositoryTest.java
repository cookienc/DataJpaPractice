package data.dataJpaPractice.repository;

import data.dataJpaPractice.model.Member;
import data.dataJpaPractice.service.MemberService;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

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
//		log.info("홍길동이 비영속 상태 인가요? {}", entityInformation.isNew(홍길동));
//		log.info("홍길동 ID = {}", 홍길동.getId());
//
//		Member 결과 = memberRepository.save(홍길동);

		Member 길동 = new Member("길동");
		Member 결과 = memberRepository.save(길동);

		assertThat(결과).isEqualTo(길동);
	}

	@DisplayName("회원_찾기")
	@Test
	void 찾기() {
//		log.info("홍길동이 비영속 상태 인가요? {}", entityInformation.isNew(홍길동));
//		log.info("홍길동 ID = {}", 홍길동.getId());
//
//		Member 저장됨 = memberRepository.save(홍길동);
		Member 길동 = new Member("길동");
		Member 저장됨 = memberRepository.save(길동);
		Optional<Member> 결과 = memberRepository.findById(저장됨.getId());

		assertThat(결과).hasValue(길동);
	}

	@DisplayName("삭제 테스트")
	@Test
	void removed() {
		Member 홍길동 = memberRepository.save(new Member("홍길동"));

		Long id = 홍길동.getId();
		memberRepository.deleteById(id);

		assertThat(memberRepository.findById(id));
	}

	@DisplayName("삭제 후 저장 테스트")
	@Test
	void 삭제_후_저장_테스트() {
		//given
		memberRepository.save(new Member("홍길동"));
		memberRepository.save(new Member("김철수"));

		//when
		//then
		assertThatCode(() -> memberService.deleteAndSave(List.of(
				new Member("홍길동"),
				new Member("김철수"),
				new Member("이영희")
		))).doesNotThrowAnyException();
	}
}
