package org.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by user on 2017/2/22.
 */
public class SeckillCloseException extends SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
