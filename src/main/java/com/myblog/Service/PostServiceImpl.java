package com.myblog.Service;

import com.myblog.Entity.Post;
import com.myblog.Exception.ResourceNotfound;
import com.myblog.Payload.PostDto;
import com.myblog.Payload.PostResponse;
import com.myblog.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private ModelMapper modelMapper;
    public PostServiceImpl(ModelMapper modelMapper)
    {
        this.modelMapper=modelMapper;

    }

    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public PostDto savedto(PostDto postDto) {
        Post post = maptoEntity(postDto);
        Post post1 = postRepository.save(post);

        PostDto dto = mapToDto(post1);
        return dto;
    }


    @Override
   public void deletePost(long id) {
        postRepository.deleteById(id);
  }
//    @Override
//    public PostDto Updatepost(long id, PostDto postDto) {
//
//        Post post1=postRepository.findById(id).orElseThrow(()-> new ResourceNotfound("Post not found"+id));
//
//       post1.setContent(postDto.getContent());
//       post1.setDescription(postDto.getDescription());
//       post1.setTitle(postDto.getTitle());
//
//        Post updatesave = postRepository.save(post1);
//        PostDto dto = mapToDto(updatesave);
//
//        return dto;
//    }

    @Override
    public PostDto getpostbyId(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotfound("id not found" + id));
        PostDto dto = mapToDto(post);
        return dto;

    }

    @Override
    public PostResponse getallpost(int pageNo , int pageSize, String sortBy, String sortDir) {
  Sort sort   =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
        Sort.by(sortBy).ascending():
        Sort.by(sortBy).descending();

        Pageable pageable=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> allpage = postRepository.findAll(pageable);

        List<Post> posts = allpage.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setPostDto(postDtos);
        postResponse.setPageNo(allpage.getNumber());
        postResponse.setPageSize(allpage.getSize());
        postResponse.setTotalelement(allpage.getTotalElements());
        postResponse.setTotalpage(allpage.getTotalPages());
        postResponse.setLast(allpage.isLast());


        return postResponse;
    }

    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
      return dto;
    }
   Post maptoEntity(PostDto postDto) {
       Post post = modelMapper.map(postDto, Post.class);
       return post;
   }
}
