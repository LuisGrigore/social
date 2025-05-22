package com.social.posts_details.services;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDetailsCreatedEvent;
import com.social.posts_details.model.PostDetailsEntity;
import com.social.posts_details.producers.PostEventProducer;
import com.social.posts_details.repos.PostDetailsRepos;
import com.social.posts_details.services.implementations.PostDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostDetailsServiceImplTest {

    @Mock
    private PostDetailsRepos postDetailsRepos;

    @Mock
    private PostEventProducer postEventProducer;

    @InjectMocks
    private PostDetailsServiceImpl postDetailsService;

    @Test
    void testSavePostDetails_shouldSaveAndProduceEvent() {
        // Arrange
        PostCreateEvent postCreateEvent = new PostCreateEvent(1L, "testPost", "testContentUrl", 100L); // Assuming user ID is 100L
        PostDetailsEntity savedEntity = PostDetailsEntity.builder()
                .id(123L) // Assume this ID is generated
                .owner(postCreateEvent.owner())
                .postName(postCreateEvent.postName())
                .downloadUrl(postCreateEvent.contentUrl())
                .build();

        when(postDetailsRepos.save(any(PostDetailsEntity.class))).thenReturn(savedEntity);
        doNothing().when(postEventProducer).producePostDetailsCreatedEvent(any(PostDetailsCreatedEvent.class));

        // Act
        postDetailsService.savePostDetails(postCreateEvent);

        // Assert
        verify(postDetailsRepos, times(1)).save(any(PostDetailsEntity.class));
        
        ArgumentCaptor<PostDetailsCreatedEvent> eventCaptor = ArgumentCaptor.forClass(PostDetailsCreatedEvent.class);
        verify(postEventProducer, times(1)).producePostDetailsCreatedEvent(eventCaptor.capture());
        
        PostDetailsCreatedEvent capturedEvent = eventCaptor.getValue();
        assertEquals(savedEntity.getId(), capturedEvent.id());
        assertEquals(savedEntity.getOwner(), capturedEvent.owner());
        assertEquals(savedEntity.getPostName(), capturedEvent.postName());
        assertEquals(savedEntity.getDownloadUrl(), capturedEvent.contentUrl());
    }
}
