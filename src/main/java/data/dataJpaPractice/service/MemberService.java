package data.dataJpaPractice.service;

import data.dataJpaPractice.model.Member;
import data.dataJpaPractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void deleteAndSave(List<Member> members) {
		memberRepository.deleteAllInBatch();
		memberRepository.saveAll(members);
	}
}
