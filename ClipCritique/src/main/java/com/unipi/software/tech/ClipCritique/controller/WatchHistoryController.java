package com.unipi.software.tech.ClipCritique.controller;

import com.unipi.software.tech.ClipCritique.model.User;
import com.unipi.software.tech.ClipCritique.model.WatchHistory;
import com.unipi.software.tech.ClipCritique.model.secondary.ReviewRequest;
import com.unipi.software.tech.ClipCritique.service.WatchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/review")
@RequiredArgsConstructor
public class WatchHistoryController {

    private final WatchHistoryService watchHistoryService;

    @PostMapping(path = "/")
    public ResponseEntity<WatchHistory> addNewWatchHistory(@RequestBody WatchHistory watchHistory) {
        try {
            WatchHistory addedWatchHistory = watchHistoryService.addNewHistory(watchHistory);
            return new ResponseEntity<>(addedWatchHistory, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/all/{video_id}")
    public List<WatchHistory> findAllReviewsByVideoId(@PathVariable("video_id") Long video_id) {
        return watchHistoryService.getAllReviewsByVideoId(video_id);

    }

    @PutMapping(path = "/review")
    public ResponseEntity<HttpStatus> updateHistory(@RequestBody ReviewRequest request) {
        watchHistoryService.updateRating(request.getVideo_id(), request.user, request.rating);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
