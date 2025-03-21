package com.example.demo.member;

import com.example.demo.shared.Address;
import com.example.demo.validation.ValidEmail;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
	public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @PostMapping
    public void registerNewMember(
        @Valid @RequestBody Member member) { // @Valid sorgt dafür, dass die Validierung der E-Mail-Adresse durchgeführt wird bevore Service aufgerufen wird
            memberService.addMember(member);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMember(
        @PathVariable("id") Long id) {
            memberService.deleteMember(id);
    }

    @PutMapping(path = "{id}")
    public void updateMember(
        @PathVariable Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) @ValidEmail String email,
        @RequestParam(required = false) Address address) {
            memberService.updateMember(id, name, email, address);
    }  

    // favorite_books Tabelle

    @GetMapping("/all-favorites")
    public List<Map<String, Long>> getAllFavorites() { // da wir kien Entity für Favoriten haben, geben wir eine Liste von Maps zurück
        return memberService.getAllFavorites();
    }

    @PostMapping("/{memberId}/book/{bookId}")
    public void addFavoriteBook(
        @PathVariable Long memberId, 
        @PathVariable Long bookId){
            memberService.addFavoriteBook(memberId, bookId);
    }

    @DeleteMapping("/{memberId}/favorites/{bookId}")
    public void removeFavoriteBook(
        @PathVariable Long memberId, 
        @PathVariable Long bookId) {
            memberService.removeFavoriteBook(memberId, bookId);
    }
}
