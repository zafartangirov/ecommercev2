package com.example.demo.repo;

import com.example.demo.entity.Attachment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AttachmentRepositoryTest {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Test
    public void testSaveAttachment() {
        Attachment attachment = new Attachment();
        attachment.setFileName("testFile.txt");
        Attachment savedAttachment = attachmentRepository.save(attachment);

        assertNotNull(savedAttachment);
    }
}