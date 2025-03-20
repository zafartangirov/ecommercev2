package com.example.demo.service;


import com.example.demo.entity.Attachment;
import com.example.demo.entity.AttachmentContent;
import com.example.demo.repo.AttachmentContentRepository;
import com.example.demo.repo.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentService {

    private final AttachmentContentRepository attachmentContentRepository;
    private final AttachmentRepository attachmentRepository;

    public AttachmentService(AttachmentContentRepository attachmentContentRepository, AttachmentRepository attachmentRepository) {
        this.attachmentContentRepository = attachmentContentRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public void getFileById(Integer id, HttpServletResponse response) throws IOException {
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(id);
        response.getOutputStream().write(attachmentContent.getContent());
    }

    public Integer uploadFile(MultipartFile file) throws IOException {
        Attachment attachment = Attachment.builder()
                .fileName(file.getOriginalFilename())
                .build();
        attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = AttachmentContent.builder()
                .content(file.getBytes())
                .attachment(attachment)
                .build();
        attachmentContentRepository.save(attachmentContent);
        return attachment.getId();
    }
}
