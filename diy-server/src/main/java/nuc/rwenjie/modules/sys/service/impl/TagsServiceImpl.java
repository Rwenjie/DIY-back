package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.modules.sys.entity.TagsEntity;
import nuc.rwenjie.modules.sys.mapper.TagsMapper;
import nuc.rwenjie.modules.sys.service.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-22
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, TagsEntity> implements ITagsService {

    @Autowired
    TagsMapper tagsMapper;
    /**
     * 查询所以的Tag
     *
     * @return java.lang.String
     **/
    @Override
    public List<TagsEntity> getAllTags() {
        return tagsMapper.selectList(new QueryWrapper<TagsEntity>());
    }

    @Override
    public TagsEntity getTagById(Long tid) {
        return tagsMapper.selectById(tid);
    }
}
