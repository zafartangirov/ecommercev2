package com.example.demo.service;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.AttachmentContent;
import com.example.demo.repo.AttachmentContentRepository;
import com.example.demo.repo.AttachmentRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AttachmentServiceTest {

    @Mock
    private AttachmentContentRepository attachmentContentRepository;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private AttachmentService attachmentService;

    @Test
    public void testGetFileById() throws IOException {
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setContent(new byte[]{1, 2, 3});
        when(attachmentContentRepository.findByAttachmentId(1)).thenReturn(attachmentContent);

        OutputStream outputStream = mock(OutputStream.class);
        when(response.getOutputStream()).thenReturn((ServletOutputStream) outputStream);

        attachmentService.getFileById(1, response);
        verify(response.getOutputStream()).write(attachmentContent.getContent());
    }

    @Test
    public void testUploadFile() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("testfile.txt");
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});

        attachmentService.uploadFile(file);
        verify(attachmentRepository, times(1)).save(any(Attachment.class));
        verify(attachmentContentRepository, times(1)).save(any(AttachmentContent.class));
    }
}
