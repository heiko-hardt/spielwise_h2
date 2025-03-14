package com.example.demo.member;

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

    public void addFavoriteBook(Long memberId, Long BookId){

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalStateException("Mitglied mit ID " + memberId + " existiert nicht"));

        Book book = bookRepository.findById(BookId)
            .orElseThrow(() -> new IllegalStateException("Buch mit ID " + BookId + " existiert nicht"));
        
        member.getFavoriteBooks().add(book);
        memberRepository.save(member);

    }
}
