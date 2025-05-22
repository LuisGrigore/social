package com.social.feed.consummers;

import com.social.common.events.PostDetailsCreatedEvent;
import com.social.feed.services.FeedService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostEventsConsumerTest {

    @Mock
    private FeedService feedService;

    @InjectMocks
    private PostEventsConsumer postEventsConsumer;

    @Test
    void testConsumePostDetailsCreatedEvent_shouldCallFeedService() {
        // Arrange
        PostDetailsCreatedEvent event = new PostDetailsCreatedEvent(1L, 100L, "Test Post", "http://example.com/post.jpg");
        doNothing().when(feedService).addPostToFeeds(any(PostDetailsCreatedEvent.class));

        // Act
        postEventsConsumer.consumePostDetailsCreatedEvent(event);

        // Assert
        verify(feedService, times(1)).addPostToFeeds(event);
    }
}
