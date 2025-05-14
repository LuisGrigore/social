package com.social.posts_details.services.implementations;
import com.social.common.dtos.PostDeleteRequest;
import com.social.common.dtos.PostDeleteResponse;
import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;
import com.social.posts_details.exceptions.PostNotFoundException;
import com.social.posts_details.model.PostDetailsEntity;
import com.social.posts_details.producers.PostEventProducer;
import com.social.posts_details.repos.PostDetailsRepos;
import com.social.posts_details.services.PostDetailsService;
import jakarta.ws.rs.NotAuthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostDetailsServiceImpl implements PostDetailsService {

    private final PostDetailsRepos postDetailsRepos;
    private final PostEventProducer postEventProducer;

    @Override
    public void savePostDetails(PostCreateEvent postCreateEvent) {
        postDetailsRepos.save(PostDetailsEntity.builder()
                        .owner(postCreateEvent.owner())
                        .postName(postCreateEvent.postName())
                        .downloadUrl(postCreateEvent.contentUrl())
                        .build());
    }


    @Override
    public PostDeleteResponse deletePost(PostDeleteRequest postDeleteRequest, Long id) throws PostNotFoundException{
        Optional<PostDetailsEntity> postDetailsEntity = postDetailsRepos.findById(postDeleteRequest.id());
        if(postDetailsEntity.isEmpty())
            throw new PostNotFoundException("Post with id: " + postDeleteRequest.id() + " not found.");
        if(!postDetailsEntity.get().getOwner().equals(id))
            throw new NotAuthorizedException("Post with id: " + postDeleteRequest.id() + " can only be deleted by its owner." );
        postDetailsRepos.deleteById(postDeleteRequest.id());
        postEventProducer.producePostDeleteEvent(new PostDeleteEvent(postDetailsEntity.get().getId(), postDetailsEntity.get().getPostName()));
        return new PostDeleteResponse(postDeleteRequest.id());
    }
}
