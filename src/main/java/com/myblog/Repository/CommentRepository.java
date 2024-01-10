package com.myblog.Repository;

import com.myblog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment , Long>{
}
