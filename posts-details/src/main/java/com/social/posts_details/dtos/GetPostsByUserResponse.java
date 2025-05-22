package com.social.posts_details.dtos;

import com.social.common.dtos.PostGetResponse;

import java.util.List;

public record GetPostsByUserResponse(List<PostGetResponse> postGetResponses) {
}
