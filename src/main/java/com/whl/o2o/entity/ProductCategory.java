package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Data
public class ProductCategory {
    private Long productCategoryId;
    private Long shopId;
    private String productCategoryName;
    private Integer priority;
    private Date createTime;
}
