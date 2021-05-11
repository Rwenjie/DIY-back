package nuc.rwenjie.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper extends BaseMapper<UserDO> {

}
