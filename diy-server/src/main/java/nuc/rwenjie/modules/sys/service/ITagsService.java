package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.TagsEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-22
 */
public interface ITagsService extends IService<TagsEntity> {

    /**
     * 查询所以的Tag
     * @return java.lang.String
     **/
    List<TagsEntity> getAllTags();

    TagsEntity getTagById(Long tid);
}
