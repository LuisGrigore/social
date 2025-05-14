package com.social.posts_details.dtos;

import java.util.List;

public record GetPostsByUserResponse(List<PostGetResponse> postGetResponses) {
}
