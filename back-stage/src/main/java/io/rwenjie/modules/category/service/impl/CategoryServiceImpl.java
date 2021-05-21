package io.rwenjie.modules.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.rwenjie.common.utils.PageUtils;
import io.rwenjie.common.utils.Query;
import io.rwenjie.modules.category.dao.CategoryDao;
import io.rwenjie.modules.category.entity.CategoryEntity;
import io.rwenjie.modules.category.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

}