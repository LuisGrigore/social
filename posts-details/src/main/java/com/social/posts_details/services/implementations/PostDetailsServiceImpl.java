package com.social.posts_details.services.implementations;
import com.social.common.dtos.PostDeleteRequest;
import com.social.common.dtos.PostDeleteResponse;
import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;
import com.social.common.events.PostDetailsCreatedEvent;
import com.social.posts_details.dtos.GetPostsByUserResponse;
import com.social.posts_details.dtos.PostGetResponse;
import com.social.common.exceptions.PostNotFoundException;
import com.social.posts_details.model.PostDetailsEntity;
import com.social.posts_details.producers.PostEventProducer;
import com.social.posts_details.repos.PostDetailsRepos;
import com.social.posts_details.services.PostDetailsService;
import jakarta.ws.rs.NotAuthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostDetailsServiceImpl implements PostDetailsService {

    private final PostDetailsRepos postDetailsRepos;
    private final PostEventProducer postEventProducer;

    @Override
    @CachePut(value = "posts", key = "#result.id")
    public PostDetailsEntity savePostDetails(PostCreateEvent postCreateEvent) {
        PostDetailsEntity savedEntity = postDetailsRepos.save(PostDetailsEntity.builder()
                .owner(postCreateEvent.owner())
                .postName(postCreateEvent.postName())
                .downloadUrl(postCreateEvent.contentUrl())
                .build());

        PostDetailsCreatedEvent event = new PostDetailsCreatedEvent(
                savedEntity.getId(),
                savedEntity.getOwner(),
                savedEntity.getPostName(),
                savedEntity.getDownloadUrl()
        );
        postEventProducer.producePostDetailsCreatedEvent(event);

        return savedEntity;
    }


    @Override
    @CacheEvict(value = "posts", key = "#postDeleteRequest.id")
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

    @Override
    @CacheEvict(value = "posts", key = "#postDeleteRequest.id")
    public void deletePostsByOwnerId(Long owner_id){
        postDetailsRepos.findByOwner(owner_id)
                .forEach(postDetailsEntity -> {
                    postDetailsRepos.delete(postDetailsEntity);
                    postEventProducer.producePostDeleteEvent(new PostDeleteEvent(postDetailsEntity.getId(), postDetailsEntity.getPostName()));
                    evictPostFromCache(postDetailsEntity.getId());
                });
    }
    @CacheEvict(value = "posts", key = "#postId")
    private void evictPostFromCache(Long postId) {
    }

    @Override
    public GetPostsByUserResponse getPostsByUserId(Long owner_id) {
        List<PostGetResponse> postGetResponses = postDetailsRepos.findByOwner(owner_id)
                .stream()
                .map(postDetailsEntity -> new PostGetResponse(postDetailsEntity.getOwner(), postDetailsEntity.getDownloadUrl()))
                .toList();
        return new GetPostsByUserResponse(postGetResponses);
    }

    @Override
    public boolean existsById(Long id) {
        return postDetailsRepos.existsById(id);
    }
}
