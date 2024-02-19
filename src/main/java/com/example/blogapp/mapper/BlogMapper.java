package com.example.blogapp.mapper;

import com.example.blogapp.entity.Blog;
import com.example.blogapp.model.CreateBlogRequest;
import com.example.blogapp.model.UpdateBlogRequest;
import com.example.blogapp.model.dto.BlogDetailsDto;
import com.example.blogapp.model.dto.BlogDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BlogMapper {

    @Mapping(target = "id", ignore = true)
    Blog mapRequestToBlog(CreateBlogRequest createRequest);

    @Mapping(target = "id", ignore = true)
    Blog mapUpdateRequestToBlog(UpdateBlogRequest updateRequest);

    BlogDto mapEntityToResponse(Blog blog);

    BlogDetailsDto mapEntityToBlogDetailsResponse(Blog blog);


}
