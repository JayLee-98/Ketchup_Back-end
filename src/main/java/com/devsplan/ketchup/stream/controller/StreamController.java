package com.devsplan.ketchup.stream.controller;

import com.devsplan.ketchup.stream.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stream")
public class StreamController {

    @Autowired
    private StreamService streamService;

    @GetMapping("/token/{userId}")
    public ResponseEntity<String> getUserToken(@PathVariable String userId) {
        String token = streamService.generateUserToken(userId);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/user/sync")
    public ResponseEntity<Void> syncUser(@RequestParam String userId, @RequestParam String userName) {
        streamService.syncUser(userId, userName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/channel/create")
    public ResponseEntity<Void> createChannel(@RequestParam String channelId, @RequestParam String userId) {
        streamService.createChannel(channelId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/channel/{channelId}/member/add")
    public ResponseEntity<Void> addMember(@PathVariable String channelId, @RequestParam String memberId) {
        streamService.addMember(channelId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/channel/{channelId}/member/remove")
    public ResponseEntity<Void> removeMember(@PathVariable String channelId, @RequestParam String memberId) {
        streamService.removeMember(channelId, memberId);
        return ResponseEntity.ok().build();
    }
}


