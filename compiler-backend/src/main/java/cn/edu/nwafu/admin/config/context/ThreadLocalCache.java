package cn.edu.nwafu.admin.config.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/18 6:46 下午
 */
public class ThreadLocalCache
{
    private static final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    public static Object get(String key) {
        return threadLocal.get().getOrDefault(key, null);
    }


    public static void set(String key, Object value) {
        threadLocal.get().put(key, value);
    }

    public static void remove(String key) {
        threadLocal.get().remove(key);
    }

    public static void clear() {
        threadLocal.set(new HashMap<>());
    }
}
