package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repositories.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    public MemberResponse addMember(MemberRequest memberRequest){
        //if(memberRepository.existsById())
        Member newMember = MemberRequest.getMemberEntity(memberRequest);
        newMember = memberRepository.save(newMember);
        return new MemberResponse(newMember , false);
    }

    public List<MemberResponse> getMembers(boolean includeAll){
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = new ArrayList<>();
      /*  for (Member m: members) {
            MemberResponse mr = new MemberResponse(m, true);
            memberResponses.add(mr);
        }
       */
        memberResponses = members.stream().map(m-> new MemberResponse(m,includeAll)).toList();
        return memberResponses;
    }

    public Member findMemberById(String username){
       return memberRepository.findById(username).
               orElseThrow(() -> new EntityNotFoundException("member not found"));
    }


    public MemberResponse getMemberById(String username) {
        Member member = findMemberById(username);
        return new MemberResponse(member, true);
    }

    public void setSpecificUserRanking(String username, int value) {
        Member member = findMemberById(username);
        member.setRanking(value);
        memberRepository.save(member);
    }

    public ResponseEntity<Boolean> editMember(MemberRequest body, String username){
        Member editThis = memberRepository.findById(username).
                orElseThrow(()-> new EntityNotFoundException("nothing to edit"));
        if (body.getEmail() != null){
            editThis.setEmail(body.getEmail());
        }
        if (body.getPassword() != null){
            editThis.setPassword(body.getPassword());
        }
        if (body.getFirstName() != null){
            editThis.setFirstName(body.getFirstName());
        }
        if (body.getLastName() != null){
            editThis.setLastName(body.getLastName());
        }
        if (body.getStreet() != null){
            editThis.setStreet(body.getStreet());
        }
        if (body.getCity() != null){
            editThis.setCity(body.getCity());
        }
        if (body.getZip() != null){
            editThis.setZip(body.getZip());
        }
        memberRepository.save(editThis);



        return ResponseEntity.ok(true);
    }

    public void deleteMember(String username) {

            memberRepository.deleteById(username);


    }
}
