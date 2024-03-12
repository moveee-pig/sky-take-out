package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {
    /**
     * 新增菜品及其口味
     **/
    public void saveWithFlavor(DishDTO dishDTO);
    /**
     * 菜品分页查询
     **/
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
    /**
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);
}