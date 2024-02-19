package com.example.blogapp.mapper;

import com.example.blogapp.entity.Student;
import com.example.blogapp.model.StudentUpdateRequest;
import com.example.blogapp.model.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student mapRequestToStudent(StudentUpdateRequest updateRequest);

    StudentDto mapEntityToResponse(Student student);
}
