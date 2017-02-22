package org.seckill.dto;

/**
 * 暴露秒杀地址DTO
 * Created by 胡庆泽 on 2017/2/22.
 */
public class Exposer {

    //是否开启秒杀
    private boolean exposed;

    //一种加密措施
    private long md5;

    private long seckillId;

    //系统当前时间（毫秒）
    private long now;

    //开启时间
    private long start;

    //结束时间
    private long end;

    public Exposer(boolean exposed, long md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long now, long start, long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public long getMd5() {
        return md5;
    }

    public void setMd5(long md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
