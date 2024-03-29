package net.mikoto.yukino.strategy;

/**
 * @author mikoto
 * &#064;date 2022/12/25
 * Create for yukino
 */
public interface PrimaryKeyGenerateStrategy<T> extends Strategy<T> {
    T run(Object... objects);
}
