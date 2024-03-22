package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
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
    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    void start0rstop(Integer status, Long id);

    List<DishVO> listWithFlavor(Dish dish);
}
