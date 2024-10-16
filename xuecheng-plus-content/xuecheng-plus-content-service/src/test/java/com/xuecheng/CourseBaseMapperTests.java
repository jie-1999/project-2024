package com.xuecheng;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.mapper.CourseBaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author wangjie
 * @Date 2024/10/15 上午11:04
 */
@SpringBootTest
public class CourseBaseMapperTests {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Test
    void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(74L);
        Assertions.assertNotNull(courseBase);
        //测试查询接口
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();

        queryCourseParamsDto.setCourseName("课程1");
        //审核状态
        queryCourseParamsDto.setAuditStatus("202004");
        //发布状态
        queryCourseParamsDto.setPublishStatus("203001");
        //拼接查询条件
        //根据课程名称模糊查询  name like '%名称%'
        //Wrapper方法通常接受一个 boolean 类型的参数，用于决定是否将该条件加入到最终的 SQL 中。例如：
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName());
        //根据课程审核状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());

        //分页参数
        PageParams pageParams = new PageParams();

        pageParams.setPageNo(1L);//页码
        pageParams.setPageSize(3L);//每页记录数
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        //分页查询E page 分页参数, @Param("ew") Wrapper<T> queryWrapper 查询条件
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        //数据
        List<CourseBase> items = pageResult.getRecords();
        //总记录数
        long total = pageResult.getTotal();
        //准备返回数据 List<T> items, long counts, long page, long pageSize
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
        System.out.println(courseBasePageResult);

    }
}
