package com.myblog.Service;

import com.myblog.Payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
