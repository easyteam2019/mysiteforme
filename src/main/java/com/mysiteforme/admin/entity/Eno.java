package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
public class Eno  extends DataEntity<Eno> {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名字
     */
	private String eno;

    /**
     * 发件国家
     */
    private String snation;


    /**
     * 寄件省份编号
     */
    private String sprovince;

    /**
     * 寄件城市编号
     */
    private String scity;

    /**
     * 寄件县/区编号
     */
    private String scounty;

    /**
     * 发寄件地址
     */
    private String saddress1;

    /**
     * 收件国家编号
     */
    private String rnation;

    /**
     * 发收件省份编号
     */
    private String rprovince;

    /**
     * 收件城市编号
     */
    private String rcity;

    /**
     * 收件县/区编号
     */
    private String rcounty;

    /**
     * 发收件地址
     */
    private String raddress1;


    /**
     * 单号状态
     */
    private String sendstatus;

    /**
     * 快递号码等级
     */
    private String level;

    /**
     * 出单备注
     */
    private String remarks;

    /**
     * 订单号
     */
    private String order;

    /**
     * 出单日期
     */
    private String printdate;


}
