package com.myblog.Controller;

import com.myblog.Payload.PostDto;
import com.myblog.Payload.PostResponse;
import com.myblog.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {


    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<?> savedto(@Valid @RequestBody PostDto postDto, BindingResult result)
    {
        if(result.hasErrors() ){

            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.savedto(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Deletepost(@PathVariable("id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("record delete", HttpStatus.OK);//200

    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> Updatepost(@PathVariable("id")  long id , @RequestBody PostDto postDto)
    {
        PostDto dto = postService.Updatepost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
   public ResponseEntity<PostDto> getpostbyId(@PathVariable("id") long id){

        PostDto dto = postService.getpostbyId(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

@GetMapping
    public  PostResponse getallpost(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir)
{


    PostResponse postResponse= postService.getallpost(pageNo,pageSize,sortBy,sortDir);
      return postResponse;



    }
}
