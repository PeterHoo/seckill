package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatusEnum;

/**
 * 封装执行秒杀后的结果
 * Created by 胡庆泽 on 2017/2/22.
 */
public class SeckillExecution {

    private long seckillId;

    //秒杀执行结果状态
    private int status;

    //状态表示
    private String statusInfo;

    //秒杀成功对象
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatusEnum seckillStatusEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.status = seckillStatusEnum.getStatus();
        this.statusInfo = seckillStatusEnum.getStatusInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId,SeckillStatusEnum seckillStatusEnum) {
        this.seckillId = seckillId;
        this.status = seckillStatusEnum.getStatus();
        this.statusInfo = seckillStatusEnum.getStatusInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", status=" + status +
                ", statusInfo='" + statusInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
