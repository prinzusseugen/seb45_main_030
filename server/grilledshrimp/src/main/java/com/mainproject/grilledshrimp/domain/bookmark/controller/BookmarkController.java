package com.mainproject.grilledshrimp.domain.bookmark.controller;

import com.mainproject.grilledshrimp.domain.bookmark.dto.BookmarkPostDto;
import com.mainproject.grilledshrimp.domain.bookmark.entity.Bookmark;
import com.mainproject.grilledshrimp.domain.bookmark.mapper.BookmarkMapper;
import com.mainproject.grilledshrimp.domain.bookmark.service.BookmarkService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final BookmarkMapper mapper;
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkMapper mapper, BookmarkService bookmarkService) {
        this.mapper = mapper;
        this.bookmarkService = bookmarkService;
    }

    // 북마크 생성
    @PostMapping()
    public ResponseEntity postBookmark(@Valid @RequestBody BookmarkPostDto bookmarkPostDto) {
        Bookmark bookmark = bookmarkService.createBookmark(bookmarkPostDto);
        return new ResponseEntity(mapper.bookmarkToBookmarkResponseDto(bookmark), HttpStatus.CREATED);
    }

    // 북마크 전체 조회
    @GetMapping("/{user-id}")
    public ResponseEntity getBookmark(@PathVariable("user-id") Long userId) {
        List<Bookmark> findBookmark = bookmarkService.findBookmark(userId);
        return new ResponseEntity(mapper.bookmarkListToBookmarkResponseDtoList(findBookmark), HttpStatus.OK);
    }

    // 북마크 삭제
    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteBookmark(@PathVariable("user-id") Long userId) {
        bookmarkService.deleteBookmark(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
