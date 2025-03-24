package com.example.demo.member;

import com.example.demo.shared.Address;
import com.example.demo.validation.ValidEmail;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated // Validierung für Parameter (PUT) wird aktiviert (für @Valid wird es nicht benötigt, da es auf Objecte angewendet wird)
@RequestMapping(path = "api/v1/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
	public List<Member> getMembers() {
        return memberService.getMembers();
    }

    // @Valid beom POST führt alle Validierungen durch, die wir in der Member-Klasse definiert haben.
    // Wenn ich @Valid weg lasse, wird die Validierung nicht mehr im Controller durchgeführt, aber trozdem im Service
    @PostMapping
    public void registerNewMember(
        @Valid @RequestBody Member member) { 
            memberService.addMember(member);
    }

    @DeleteMapping("{id}")
    public void deleteMember(
        @PathVariable Long id) {
            memberService.deleteMember(id);
    }

    @PutMapping(path = "{id}")
    public void updateMember(
        @PathVariable 
            Long id,

        // required=true -> Spring prüft die existenz des Parameters, before es zu minenen Validierungen kommt. 
        // Spring gibt seinen eigenen Fehler zurück, wenn der Parameter nicht vorhanden ist.
        // Danach (wenn es durch kommt) wird die Validierung durchgeführt und meine definierte message zurückgegeben.    
        @RequestParam(required = true) 
            @NotBlank(message = "Name darf nicht leer sein") // prüft nach nicht leer, nicht Leerzeichen, nicht null
            @Size(min = 2, max = 100, message = "Name muss zwischen 2 und 100 Zeichen lang sein") 
            String name,
        
        @RequestParam(required = true) 
            @NotBlank(message = "E-Mail darf nicht leer sein") 
            //@ValidEmail verwendet die ValidEmail-Annotation
            @ValidEmail
            String email,
        
        @RequestParam(required = false) 
            Address address
    ) {
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
