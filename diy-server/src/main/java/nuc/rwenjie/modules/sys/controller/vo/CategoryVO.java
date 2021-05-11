package nuc.rwenjie.modules.sys.controller.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName CategoryVO
 * @Description TODO 商品分类
 * @Date 2021/4/27 14:40
 **/

@Data
public class CategoryVO {

    private Integer id;

    private Integer value;

    private String label;

    private List<CategoryVO> children;

}
