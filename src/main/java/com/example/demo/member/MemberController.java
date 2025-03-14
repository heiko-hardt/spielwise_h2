package com.example.demo.member;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/{memberId}/favorites/{bookId}")
    public void addFavoriteBook(
        @PathVariable Long memberId, 
        @PathVariable Long bookId){
            memberService.addFavoriteBook(memberId, bookId);
    }



}
