package com.example.demo.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface AttachmentService {

    void getFileById(Integer id, HttpServletResponse response) throws IOException;

    Integer uploadFile(MultipartFile file) throws IOException;
}
