package com.xuecheng.content.service;


import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

public interface CourseBaseInfoService {


    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);


    //添加课程基本信息
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    //修改课程基本信息
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);

    //根据id查询课程信息
    CourseBaseInfoDto getCourseBaseInfo(long courseId);
}



