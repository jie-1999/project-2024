package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO 课程分类树形结构查询
 * @Author wangjie
 * @Date 2024/10/17 上午9:30
 */
public interface CourseCategoryService {

    public List<CourseCategoryTreeDto> queryTreeNodes(String id);

}
