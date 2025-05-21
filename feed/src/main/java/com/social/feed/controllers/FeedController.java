package com.social.feed.controllers;

import com.social.feed.dtos.GetFeedRepose;
import com.social.feed.services.FeedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@AllArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<GetFeedRepose> getFeed(HttpServletRequest request){
        return ResponseEntity.ok(feedService.getByUser(Long.parseLong(request.getHeader("id"))));
    }
}
