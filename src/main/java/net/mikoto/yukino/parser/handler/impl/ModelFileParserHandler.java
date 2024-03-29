package net.mikoto.yukino.parser.handler.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import net.mikoto.yukino.manager.YukinoModelManager;
import net.mikoto.yukino.strategy.PrimaryKeyGenerateStrategy;
import net.mikoto.yukino.strategy.TableNameStrategy;
import net.mikoto.yukino.model.Field;
import net.mikoto.yukino.model.YukinoModel;
import net.mikoto.yukino.model.config.InstantiableObject;
import net.mikoto.yukino.parser.handler.YukinoModelParserHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author mikoto
 * &#064;date 2022/12/11
 * Create for yukino
 */
public class ModelFileParserHandler extends YukinoModelParserHandler {
    public ModelFileParserHandler(YukinoModelManager yukinoModelManager) {
        super(yukinoModelManager);
    }

    @Override
    protected YukinoModel doParse(@NotNull File target) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (target.getName().endsWith(".yukino.model.json")) {
            JSONObject rawModel = JSON.parseObject(new FileInputStream(target));
            YukinoModel yukinoModel;

            // Add fields.
            JSONArray fields = rawModel.getJSONArray("fields");
            Field[] fieldsResult = new Field[fields.size()];
            if (fields.size() > 0 && fields.get(0) instanceof JSONArray) {
                for (int i = 0; i < fields.size(); i++) {
                    JSONArray fieldJson = fields.getJSONArray(i);
                    Field field = new Field();
                    field.setClassName(fieldJson.getString(0));
                    field.setFieldName(fieldJson.getString(1));
                    fieldsResult[i] = field;
                }

                // Instance the tableNameStrategy
                TableNameStrategy tableNameStrategy = (TableNameStrategy) InstantiableObject.instance(
                        rawModel.getObject("tableNameStrategy", InstantiableObject.class)
                );

                InstantiableObject primaryKeyGenerateStrategyObj = rawModel.getObject("primaryKeyGenerateStrategy", InstantiableObject.class);

                PrimaryKeyGenerateStrategy<?> primaryKeyGenerateStrategy = null;
                if (primaryKeyGenerateStrategyObj != null) {
                    // Instance the primaryKeyGenerateStrategy
                    primaryKeyGenerateStrategy =
                            (PrimaryKeyGenerateStrategy<?>) InstantiableObject.instance(primaryKeyGenerateStrategyObj);
                }

                rawModel.remove("fields");
                rawModel.remove("tableNameStrategy");
                rawModel.remove("primaryKeyGenerateStrategy");

                yukinoModel = rawModel.to(YukinoModel.class);
                yukinoModel.setFields(fieldsResult);
                yukinoModel.setTableNameStrategy(tableNameStrategy);
                yukinoModel.setPkGenerateStrategy(primaryKeyGenerateStrategy);


                return yukinoModel;
            }
        }
        return null;
    }
}
