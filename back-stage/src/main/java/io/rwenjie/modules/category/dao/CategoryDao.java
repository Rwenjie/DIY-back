package io.rwenjie.modules.category.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.rwenjie.modules.category.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-17 15:23:26
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
