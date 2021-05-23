package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.dataobject.CategoryDO;
import nuc.rwenjie.modules.sys.service.model.CategoryModel;

import java.util.List;
import java.util.Map;

/**
 * @Author Rwenjie
 * @ClassName CategoryService
 * @Description TODO
 * @Date 2021/4/27 13:24
 **/


public interface CategoryService extends IService<CategoryModel> {

    /**
     * 递归查所有分级的目录
     * @return java.util.List<CategoryModel>
     **/
    List<CategoryModel> selectAll();

    
    /**
     * 根据id查询分类信息
     * @Param: categoryId
     * @return nuc.rwenjie.modules.sys.service.model.CategoryModel
     **/
    CategoryModel getCategoryById(Long categoryId);

    /**
     * 根据叶子节点的分类获得
     * @Param: cid
     * @return java.util.Map<java.lang.Long,java.lang.String>
     **/
    List<Map<String, Object> > getAllCategoryByLeaf(Long cid);

    List<CategoryDO> getAllCategory();
}
