package nuc.rwenjie.modules.sys.service;

import nuc.rwenjie.modules.sys.service.model.CategoryModel;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName CategoryService
 * @Description TODO
 * @Date 2021/4/27 13:24
 **/


public interface CategoryService {

    /**
     * 递归查所有分级的目录
     * @return java.util.List<CategoryModel>
     **/
    List<CategoryModel> selectAll();
}
