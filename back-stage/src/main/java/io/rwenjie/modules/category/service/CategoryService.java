package io.rwenjie.modules.category.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.rwenjie.common.utils.PageUtils;
import io.rwenjie.modules.category.entity.CategoryEntity;

import java.util.Map;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-17 15:23:26
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

