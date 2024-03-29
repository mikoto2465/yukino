package net.mikoto.yukino.manager;

import net.mikoto.yukino.model.HasAHashMapClass;
import net.mikoto.yukino.model.YukinoModel;

/**
 * @author mikoto
 * &#064;date 2022/12/11
 * Create for yukino
 */
public class YukinoModelManager extends HasAHashMapClass<YukinoModel> {
    public void register(YukinoModel yukinoModel) {
        super.put(yukinoModel.getModelName(), yukinoModel);
    }
}