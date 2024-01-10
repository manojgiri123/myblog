package com.myblog.Service;

import com.myblog.Payload.PostDto;
import com.myblog.Payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto savedto(PostDto postDto);

    void deletePost(long id);

  //  PostDto Updatepost(long id ,PostDto postDto);

    PostDto getpostbyId(long id);

    PostResponse getallpost(int pageNo, int pageSize, String sortBy, String sortDir);


}
