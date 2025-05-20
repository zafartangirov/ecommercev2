package com.example.demo.service.impl;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.AttachmentContent;
import com.example.demo.repo.AttachmentContentRepository;
import com.example.demo.repo.AttachmentRepository;
import com.example.demo.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentContentRepository attachmentContentRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public void getFileById(Integer id, HttpServletResponse response) throws IOException {
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(id);
        response.getOutputStream().write(attachmentContent.getContent());
    }

    @Override
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
