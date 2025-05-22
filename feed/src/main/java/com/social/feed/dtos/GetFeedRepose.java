package com.social.feed.dtos;

import com.social.common.dtos.PostGetResponse;

import java.util.List;

public record GetFeedRepose(List<PostGetResponse> postGetResponses) {
}
