package net.mikoto.yukino.strategy;

/**
 * @author mikoto
 * @date 2022/12/24
 * Create for yukino
 */
public interface TableNameStrategy extends Strategy<String> {
    String run(Object... objects);
}
