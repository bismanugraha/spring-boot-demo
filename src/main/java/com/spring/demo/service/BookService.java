package com.spring.demo.service;

import com.spring.demo.dto.BookRequest;
import com.spring.demo.dto.BookResponse;
import com.spring.demo.model.BookEntity;
import com.spring.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponse getById(Long id) {
        BookEntity entity = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return toResponse(entity);
    }

    @Transactional
    public BookResponse create(BookRequest request) {
        BookEntity entity = new BookEntity(request.getName(), request.getDescription());
        BookEntity saved = bookRepository.save(entity);
        return toResponse(saved);
    }

    @Transactional
    public BookResponse update(Long id, BookRequest request) {
        BookEntity entity = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        BookEntity updated = bookRepository.save(entity);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private BookResponse toResponse(BookEntity entity) {
        return new BookResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}