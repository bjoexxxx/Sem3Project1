package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repositories.MemberRepository;
import dat3.car.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Admin only
    @GetMapping
    List<MemberResponse> getMembers(){ return memberService.getMembers(true);}

    //Admin ???
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.getMemberById(username);}

    //Anon --> ??????
    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    //Member
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        memberService.editMember(body, username);
        return ResponseEntity.ok(true);
    }

    //Admin ????
    @PatchMapping("/ranking/{username}/{value}")
    void setRankingForUser(@PathVariable String username, @PathVariable int value) {
         memberService.setSpecificUserRanking(username, value);
    }

    // Admin ????
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {
        memberService.deleteMember(username);
    }

}
