package com.example.blogapp.mapper;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.model.CommentRequest;
import com.example.blogapp.model.dto.BlogCommentsDto;
import com.example.blogapp.model.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    Comment mapRequestToComment(CommentRequest commentRequest);

    CommentDto mapEntityToResponse(Comment comment);

    BlogCommentsDto mapEntityToBlogCommentsDto(Comment comment);
}
