package nuc.rwenjie.modules.sys.service.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Rwenjie
 * @ClassName PayMethodModel
 * @Description TODO 支付方式
 * @Date 2021/5/8 22:47
 **/

@Data
public class PayMethodModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String label;
}
