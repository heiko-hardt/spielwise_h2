package com.example.demo.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.shared.Address;

@Service
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public MemberService(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    public List<Member> getMembers() {
		return memberRepository.findAll();
	}

    public void addMember(Member member) {
        Optional<Member> existingMember = memberRepository.findByName(member.getName());
        if (existingMember.isPresent())
            throw new IllegalStateException(member.getName() + " existiert bereits");  
        
        memberRepository.save(member);    
    }

    public void deleteMember(Long id) {
		boolean exists = memberRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Mitglied mit ID " + id + " existiert nicht");
		}
		memberRepository.deleteById(id);
	}

    @Transactional // explizites Speichern nicht nötig - wird automatisch gemacht, weil @Transactional alle Änderungen merkt und speichert
    public void updateMember(Long id, String name, String email, Address address) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Mitglied mit ID " + id + " existiert nicht"));
        
        if (name != null && name.length() > 0 && !name.equals(member.getName())) {
            member.setName(name);
        }
        if (email != null && email.length() > 0 && !email.equals(member.getEmail())) {
            member.setEmail(email);
        }
        if (address != null) {
            member.setAddress(address);
        }
    }

    
    // favorite_books Tabelle

    public List<Map<String, Long>> getAllFavorites() {
        List<Object[]> results = memberRepository.findAllFavoriteBooks();
                                                                                                                                                                                                                    
        List <Map<String, Long>> favorites = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Long> favorite = new HashMap<>();
            favorite.put("memberId", (Long) row[0]);
            favorite.put("bookId", (Long) row[1]);
            favorites.add(favorite);
        }
        return favorites;
    }

    public void addFavoriteBook(Long memberId, Long bookId){
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalStateException("Mitglied mit ID " + memberId + " existiert nicht"));

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalStateException("Buch mit ID " + bookId + " existiert nicht"));

        if (member.getFavoriteBooks().contains(book)) {  // Verhindert Duplikate
            throw new IllegalStateException("Buch mit ID " + bookId + " ist bereits in den Favoriten von Mitglied " + memberId);       
        }
        member.getFavoriteBooks().add(book);
        memberRepository.save(member);
    }

    public void removeFavoriteBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalStateException("Mitglied mit ID " + memberId + " existiert nicht"));
    
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalStateException("Buch mit ID " + bookId + " existiert nicht"));
    
        // Prüfen, ob das Buch wirklich in den Favoriten ist
        if (!member.getFavoriteBooks().contains(book)) {
            throw new IllegalStateException("Buch mit ID " + bookId + " ist nicht in den Favoriten von Mitglied " + memberId);
        }
        member.getFavoriteBooks().remove(book);
        memberRepository.save(member);
    }
}
