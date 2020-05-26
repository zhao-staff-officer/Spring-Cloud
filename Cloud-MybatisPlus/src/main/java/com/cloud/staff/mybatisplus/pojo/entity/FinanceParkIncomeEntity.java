package com.cloud.staff.mybatisplus.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 停车场收入明细
 * </p>
 *
 * @author 赵参谋
 * @since 2020-04-27
 */
@Data
public class FinanceParkIncomeEntity extends DatabaseEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 停车场收入明细ID
     */
      private String incomeId;

    /**
     * 停车场ID
     */
    private String parkinglotId;

    /**
     * 值班人员ID
     */
    private String dutyUserId;

    /**
     * 项目类别（0停车消费；1包月消费；2充值；3产品消费-月租；4产品消费-长租；5产品消费-小时；6车位预约；99退款）
     */
    private Integer incomeCategory;

    /**
     * 平台ID
     */
    private String brandId;

    /**
     * 物业公司ID（对应单位ID）
     */
    private String estateId;

    /**
     * 代理商ID（对应单位ID）
     */
    private String agentId;

    /**
     * 年（2017）
     */
    private Integer dateYear;

    /**
     * 年月（201701）
     */
    private Integer dateMonth;

    /**
     * 年月日（20170101）
     */
    private Integer dateDay;

    /**
     * 记账时间
     */
    private LocalDateTime incomeTime;

    /**
     * 项目名称（车牌号或包月车主名信息）
     */
    private String incomeName;

    /**
     * 交易记录ID
     */
    private String tradeId;

    /**
     * 数据ID（对应业务数据ID）
     */
    private String dataId;

    /**
     * 免费类别
     */
    private Integer freeType;

    /**
     * 停车类别
     */
    private Integer parkingType;

    /**
     * 入场时间
     */
    private LocalDateTime entranceTime;

    /**
     * 入场通道
     */
    private String entranceId;

    /**
     * 出场时间
     */
    private LocalDateTime exitTime;

    /**
     * 出场通道
     */
    private String exitId;

    /**
     * 车牌颜色
     */
    private Integer licenseColor;

    /**
     * 车辆类别
     */
    private Integer carType;

    /**
     * 月租开始日期
     */
    private LocalDateTime monthlyStart;

    /**
     * 月租结束日期
     */
    private LocalDateTime monthlyEnd;

    /**
     * 月租单月金额
     */
    private BigDecimal monthlyAmount;

    /**
     * 承租人
     */
    private String monthlyOwner;

    /**
     * 承租车位数
     */
    private Integer monthlySpace;

    /**
     * 消费总额
     */
    private BigDecimal amountTotal;

    /**
     * 优惠/抵扣金额
     */
    private BigDecimal amountDiscount;

    /**
     * 优惠付款金额(只作为计算使用)
     */
    private BigDecimal amountDiscountPayed;

    /**
     * 应收金额
     */
    private BigDecimal amountReceivable;

    /**
     * 实际付款金额
     */
    private BigDecimal amountPayed;

    /**
     * 付款时间
     */
    private LocalDateTime payTime;

    /**
     * 付款方式付款方式（1现金；2微信；3支付宝；4京东聚合；5招行无感平台；6支付宝无感平台；7SPARK无感平台；8 ETC；9银联App；10招行App支付；11京东App支付）
     */
    private Integer payType;

    /**
     * 付款类别编码（废弃）
     */
    private Integer payTypecode;

    /**
     * 场景编码
     */
    private String sceneType;

    /**
     * 支付平台交易流水号
     */
    private String payTradeNo;

    /**
     * 对账状态（0未对账；1已对账）
     */
    private Integer payReconciliation;

    /**
     * 开票状态（0 未开票 1 已开票）
     */
    private Integer invoiceStatus;

    /**
     * 发票号码
     */
    private String fphm;

    /**
     * 开票日期
     */
    private LocalDateTime invoiceDate;

    /**
     * 发票pdf下载地址
     */
    private String pdfUrl;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private Integer delFlag;


}
