package cn.net.perfect.storage.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

/**
 * @author ChangLee
 */
@Data
@ToString(callSuper = true)
public class BaseEntity {

    public BaseEntity() {
        currentPage = 1;
        pageSize =  50;
    }

    @TableField(exist = false)
    private Integer currentPage;

    @TableField(exist = false)
    private Integer pageSize;

}
