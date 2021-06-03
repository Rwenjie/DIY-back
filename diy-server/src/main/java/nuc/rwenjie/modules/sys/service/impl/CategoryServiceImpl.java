package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.modules.sys.dataobject.CategoryDO;
import nuc.rwenjie.modules.sys.mapper.CategoryMapper;
import nuc.rwenjie.modules.sys.service.CategoryService;
import nuc.rwenjie.modules.sys.service.model.CategoryModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author Rwenjie
 * @ClassName CategoryServiceImpl
 * @Description TODO 商品分类查询
 * @Date 2021/4/27 13:24
 **/

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryModel> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<CategoryModel> selectAll() {

        List<CategoryModel> categoryModelList = new ArrayList<>();


        List<CategoryDO> categories = categoryMapper.selectAll();

        //查出parent_id=0
        for (CategoryDO category : categories) {
            if (category.getParentId() == 0) {
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

    /**
     * 根据id查询分类信息
     *
     * @param categoryId
     * @return nuc.rwenjie.modules.sys.service.model.CategoryModel
     * @Param: categoryId
     */
    @Override
    public CategoryModel getCategoryById(Long categoryId) {
        CategoryDO categoryDO = categoryMapper.selectByPrimaryKey(categoryId);
        return convertFromDataObject(categoryDO);
    }

    /**
     * 根据叶子节点的分类获得
     *
     * @param cid
     * @return java.util.Map<java.lang.Long, java.lang.String>
     * @Param: cid
     */
    @Override
    public List<Map<String, Object> > getAllCategoryByLeaf(Long cid) {
        List<Map<String, Object> > categoryList = new ArrayList<>();
        while(cid!=0){
            CategoryDO category = categoryMapper.selectByPrimaryKey(cid);
            Map<String, Object> cmap = new HashMap<>();
            cmap.put("value", category.getId());
            cmap.put("label", category.getName());
            categoryList.add(cmap);
            cid = category.getParentId();
        }
        return categoryList;
    }

    @Override
    public List<CategoryDO> getAllCategory() {
        return categoryMapper.selectAll();
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
