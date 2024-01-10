package com.myblog.Service;

import com.myblog.Entity.Comment;
import com.myblog.Entity.Post;
import com.myblog.Exception.ResourceNotfound;
import com.myblog.Payload.CommentDto;
import com.myblog.Repository.CommentRepository;
import com.myblog.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,ModelMapper modelMapper)
    {this.commentRepository=commentRepository;
        this.postRepository=postRepository;
        this.modelMapper=modelMapper;

     }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

     Comment comment  =mapToentity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotfound("not found by postid:" + postId));
        comment.setPost(post);
        Comment save = commentRepository.save(comment);

        CommentDto Dto1 = mapTodto(save);

        return Dto1 ;
    }
   private Comment mapToentity(CommentDto commentDto){
       Comment map = modelMapper.map(commentDto, Comment.class);


       return map;
   }

   private CommentDto mapTodto(Comment save){

       CommentDto dto = modelMapper.map(save, CommentDto.class);

       return dto;
   }

}
