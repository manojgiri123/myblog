package com.myblog.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min = 2,message = "title at least two char")
    private String title;
    @NotEmpty
    @Size(min = 4,message = "  at least 4 char")

    private String description;
    @NotEmpty
    @Size(min = 6,message = " content at least 6 char")
    private String content;

}
