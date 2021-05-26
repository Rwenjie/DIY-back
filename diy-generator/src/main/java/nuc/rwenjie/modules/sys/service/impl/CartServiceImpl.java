package nuc.rwenjie.modules.sys.service.impl;

import nuc.rwenjie.modules.sys.dataobject.Cart;
import nuc.rwenjie.modules.sys.mapper.CartMapper;
import nuc.rwenjie.modules.sys.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-26
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
