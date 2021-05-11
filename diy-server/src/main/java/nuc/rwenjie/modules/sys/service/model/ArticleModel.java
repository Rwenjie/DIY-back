package nuc.rwenjie.modules.sys.service.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Rwenjie
 * @ClassName articleModel
 * @Description TODO
 * @Date 2021/5/11 19:04
 **/

@Data
public class ArticleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String label;

}
