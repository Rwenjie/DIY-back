package nuc.rwenjie.modules.sys.mapper;

import nuc.rwenjie.modules.sys.dataobject.CategoryDO;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName CategoryMapper
 * @Description TODO
 * @Date 2021/4/27 13:28
 **/


public interface CategoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryDO record);

    int insertSelective(CategoryDO record);

    CategoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CategoryDO record);

    int updateByPrimaryKey(CategoryDO record);

    List<CategoryDO> selectAll();
}
