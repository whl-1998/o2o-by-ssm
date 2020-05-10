package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.PrimitiveIterator;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Data
public class ProductImg {
    private Long productImgId;
    private String imgAddr;
    private String imgDesc;
    private Integer priority;
    private Date createTime;
    private Long productId;
}
