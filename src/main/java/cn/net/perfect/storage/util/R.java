package cn.net.perfect.storage.util;

import cn.net.perfect.storage.constant.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author Lion Li
 */
@Data
@NoArgsConstructor
public class R<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    /** 状态码 */
    public static final String CODE_TAG = "code";
    
    /** 返回内容 */
    public static final String MSG_TAG = "msg";
    
    /** 数据对象 */
    public static final String DATA_TAG = "data";

    private int code;

    private String msg;

    private T data;

    public static <T> R <T> success() {
        return restResult(null, HttpStatus.SUCCESS, "操作成功");
    }

    public static <T> R <T> success(T data) {
        return restResult(data, HttpStatus.SUCCESS, "操作成功");
    }

    public static <T> R <T> success(String msg) {
        return restResult(null, HttpStatus.SUCCESS, msg);
    }

    public static <T> R <T> success(String msg, T data) {
        return restResult(data, HttpStatus.SUCCESS, msg);
    }

    public static <T> R <T> error() {
        return restResult(null, HttpStatus.ERROR, "操作失败");
    }

    public static <T> R <T> error(String msg) {
        return restResult(null, HttpStatus.ERROR, msg);
    }

    public static <T> R <T> error(T data) {
        return restResult(data, HttpStatus.ERROR, "操作失败");
    }

    public static <T> R <T> error(String msg, T data) {
        return restResult(data, HttpStatus.ERROR, msg);
    }

    public static <T> R<T> error(int code, String msg) {
        return restResult(null, code, msg);
    }
    public static <T> R<T> warn(String msg) {
        return restResult(null, HttpStatus.WARN, msg);
    }
    public static <T> R<T> warn(String msg, T data) {
        return restResult(data, HttpStatus.WARN, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static <T> Boolean isError(R<T> ret) {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(R<T> ret) {
        return HttpStatus.SUCCESS == ret.getCode();
    }
}
