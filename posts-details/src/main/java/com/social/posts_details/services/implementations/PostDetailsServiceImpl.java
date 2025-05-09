package com.social.posts_details.services.implementations;
import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;
import com.social.posts_details.model.PostDetailsEntity;
import com.social.posts_details.repos.PostDetailsRepos;
import com.social.posts_details.services.PostDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostDetailsServiceImpl implements PostDetailsService {

    private final PostDetailsRepos postDetailsRepos;

    @Override
    public void savePostDetails(PostCreateEvent postCreateEvent) {
        postDetailsRepos.save(PostDetailsEntity.builder()
                        .owner(postCreateEvent.owner())
                        .download_url(postCreateEvent.contentUrl())
                        .build());
    }

    @Override
    public void deletePostDetails(PostDeleteEvent postDeleteEvent) {

    }
}
