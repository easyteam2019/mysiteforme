package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 快递单号
 * </p>
 *
 * @author liao
 * @since 2018-01-17
 */
@TableName("eb_eno")
@Data
public class Eno implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名字
     */
	private String eno;

}
