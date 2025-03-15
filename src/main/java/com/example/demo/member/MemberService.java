package com.example.demo.member;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;

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

    public void addFavoriteBook(Long memberId, Long BookId){

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalStateException("Mitglied mit ID " + memberId + " existiert nicht"));

        Book book = bookRepository.findById(BookId)
            .orElseThrow(() -> new IllegalStateException("Buch mit ID " + BookId + " existiert nicht"));
        
        member.getFavoriteBooks().add(book);
        memberRepository.save(member);
    }
}
