package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO 课程信息管理业务接口实现类
 * @Author wangjie
 * @Date 2024/10/16 上午10:01
 */

@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    /**
     * @description: 课程查询接口
     * @author: wangjie
     * @date: 2024/10/16 上午10:02
     * @param: [pageParams, queryCourseParamsDto]
     * @return: com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
     **/
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        //构建查询条件对象
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //根据课程审核状态进行查
        queryWrapper.eq(StringUtils.isNotBlank(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());
        //构建查询条件，根据课程发布状态查询
        queryWrapper.eq(StringUtils.isNotBlank(queryCourseParamsDto.getPublishStatus()), CourseBase::getStatus, queryCourseParamsDto.getPublishStatus());
        //分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        //查询数据库
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);

        //获取数据列表
        List<CourseBase> list = pageResult.getRecords();
        //获取总记录数
        long counts = pageResult.getTotal();
        //构建返回对象
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(list, counts, pageParams.getPageNo(), pageParams.getPageSize());

        return courseBasePageResult;
    }
}
