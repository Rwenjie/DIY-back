package io.rwenjie.modules.category.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-17 15:23:26
 */
@Data
@TableName("tb_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 类目id
	 */
	@TableId
	private Long id;
	/**
	 * 类目名称
	 */
	private String name;
	/**
	 * 父类目id,顶级类目填0
	 */
	private Long parentId;
	/**
	 * 排序指数，越小越靠前
	 */
	private Integer sortOrder;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Integer status;

}
