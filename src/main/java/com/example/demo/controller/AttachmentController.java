package com.example.demo.controller;


import com.example.demo.entity.Attachment;
import com.example.demo.entity.AttachmentContent;
import com.example.demo.repo.AttachmentContentRepository;
import com.example.demo.repo.AttachmentRepository;
import com.example.demo.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class AttachmentController {

    private final AttachmentService attachmentService;


    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/{attachmentId}")
    public void getFile(@PathVariable Integer attachmentId, HttpServletResponse response) throws IOException {
        attachmentService.getFileById(attachmentId, response);
    }

    @PostMapping
    public Integer upload(@RequestParam MultipartFile file) throws IOException {
        return attachmentService.uploadFile(file);
    }
}