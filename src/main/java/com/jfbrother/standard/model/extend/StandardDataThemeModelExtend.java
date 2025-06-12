package com.jfbrother.standard.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.extend.DataOpenModelExtend;
import com.jfbrother.standard.model.StandardDataThemeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 数据开放主题管理传输对象
 *
 * @author chenyl@jfbrother.com 2022-09-16
 */
@Data
@ApiModel("数据开放主题管理传输对象extend")
@JSONType(orders = {"id", "themeName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class StandardDataThemeModelExtend extends StandardDataThemeModel {
    @ApiModelProperty(value = "数据开放列表", position = 1)
    private List<DataOpenModelExtend> dataOpenList;
}
