package nuc.rwenjie.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author Rwenjie
 * @ClassName RegisterMapper
 * @Description TODO
 * @Date 2021/3/9 21:10
 **/

@Repository
public interface RegisterMapper extends BaseMapper<UserEntity> {


}
