package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品ID查询对应的套餐id

     **/
    //select setmeal id from setmeal dish where dish_id in （1，2，3，4）
    List<Long> getsetmealIdByDishIds(List<Long> dishIds);
}
