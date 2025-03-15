package com.example.demo.member;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
	public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @PostMapping
    public void registerNewMember(@RequestBody Member member) {
        memberService.addMember(member);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMember(
        @PathVariable("id") Long id) {
            memberService.deleteMember(id);
    }

    @PutMapping(path = "{id}")
    public void updateMember(
        @PathVariable(required = true) Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email) {
            memberService.updateMember(id, name, email);
    }  

    // favorite_books Tabelle
    @PostMapping("/{memberId}/favorites/{bookId}")
    public void addFavoriteBook(
        @PathVariable Long memberId, 
        @PathVariable Long bookId){
            memberService.addFavoriteBook(memberId, bookId);
    }



}
