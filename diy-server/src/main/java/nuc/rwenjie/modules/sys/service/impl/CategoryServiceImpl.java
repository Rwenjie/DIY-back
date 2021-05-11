package nuc.rwenjie.modules.sys.service.impl;

import nuc.rwenjie.common.utils.SpringBeanFactoryUtils;
import nuc.rwenjie.modules.sys.dataobject.CategoryDO;
import nuc.rwenjie.modules.sys.mapper.CategoryMapper;
import nuc.rwenjie.modules.sys.service.CategoryService;
import nuc.rwenjie.modules.sys.service.model.CategoryModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static nuc.rwenjie.common.constant.Constant.ROOT_PARENT_ID;

/**
 * @Author Rwenjie
 * @ClassName CategoryServiceImpl
 * @Description TODO 商品分类查询
 * @Date 2021/4/27 13:24
 **/

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<CategoryModel> selectAll() {

        List<CategoryModel> categoryModelList = new ArrayList<>();

        List<CategoryDO> categories = categoryMapper.selectAll();

        //查出parent_id=0
        for (CategoryDO category : categories) {
            if (category.getParentId().equals(ROOT_PARENT_ID)) {
                CategoryModel categoryModel = new CategoryModel();
                BeanUtils.copyProperties(category, categoryModel);
                categoryModel.setValue(category.getId());
                categoryModel.setLabel(category.getName());
                categoryModelList.add(categoryModel);
            }
        }

        categoryModelList.sort(Comparator.comparing(CategoryModel::getSortOrder).reversed());

        //查询子目录
        findSubCategory(categoryModelList, categories);

        return categoryModelList;
    }


    private void findSubCategory(List<CategoryModel> categoryModelList, List<CategoryDO> categories) {
        for (CategoryModel categoryModel : categoryModelList) {
            List<CategoryModel> subCategoryModelList = new ArrayList<>();

            for (CategoryDO category : categories) {
                //如果查到内容，设置subCategory, 继续往下查
                if (categoryModel.getId().equals(category.getParentId())) {
                    CategoryModel subCategoryModel = convertFromDataObject(category);
                    subCategoryModelList.add(subCategoryModel);
                }

                subCategoryModelList.sort(Comparator.comparing(CategoryModel::getSortOrder).reversed());
                categoryModel.setChildren(subCategoryModelList);

                findSubCategory(subCategoryModelList, categories);
            }
        }
    }

    private CategoryModel convertFromDataObject(CategoryDO category) {
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(category, categoryModel);
        categoryModel.setValue(category.getId());
        categoryModel.setLabel(category.getName());
        return categoryModel;
    }
}
