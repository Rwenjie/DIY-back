package nuc.rwenjie.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.rwenjie.modules.sys.dataobject.CategoryDO;
import nuc.rwenjie.modules.sys.service.model.CategoryModel;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName CategoryMapper
 * @Description TODO
 * @Date 2021/4/27 13:28
 **/


public interface CategoryMapper extends BaseMapper<CategoryModel> {

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryDO record);

    int insertSelective(CategoryDO record);

    CategoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CategoryDO record);

    int updateByPrimaryKey(CategoryDO record);

    List<CategoryDO> selectAll();

}
