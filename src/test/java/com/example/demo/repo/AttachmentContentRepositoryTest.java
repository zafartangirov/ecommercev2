package com.example.demo.repo;

import com.example.demo.entity.AttachmentContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class AttachmentContentRepositoryTest {

    @Autowired
    private AttachmentContentRepository attachmentContentRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Test
    public void testFindByAttachmentId() {
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(attachmentRepository.findById(1).orElseThrow());
        attachmentContent.setContent(new byte[]{1, 2, 3});
        attachmentContentRepository.save(attachmentContent);

        AttachmentContent fetchedContent = attachmentContentRepository.findByAttachmentId(1);
        assertNotNull(fetchedContent);
    }

    @Test
    public void testFindByAttachmentId_NotFound() {
        AttachmentContent fetchedContent = attachmentContentRepository.findByAttachmentId(10);
        assertNull(fetchedContent);
    }
}
