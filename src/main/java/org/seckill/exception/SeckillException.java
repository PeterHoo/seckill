package org.seckill.exception;

/**
 * 秒杀业务相关异常
 * Created by user on 2017/2/22.
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
