#  20220901
> 新建数据字典子表
```sql
CREATE TABLE `data_dictionary_child` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `pid` varchar(32) DEFAULT NULL COMMENT '父id',
  `code` varchar(255) DEFAULT NULL COMMENT '代码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort_num` decimal(5,0) DEFAULT 0 COMMENT '排序号',
  `status_num` decimal(2,0) DEFAULT NULL COMMENT '状态码',
  `version_num` decimal(13,0) DEFAULT NULL COMMENT '数据版本，乐观锁',
  `is_delete` decimal(1,0) DEFAULT NULL COMMENT '逻辑删除标识符',
  `create_time` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` decimal(13,0) DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典子表';
```

> 新建数据字典表
```sql
CREATE TABLE `data_dictionary` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort_num` decimal(5,0) DEFAULT 0 COMMENT '排序号',
  `status_num` decimal(2,0) DEFAULT NULL COMMENT '状态码',
  `version_num` decimal(13,0) DEFAULT NULL COMMENT '数据版本，乐观锁',
  `is_delete` decimal(1,0) DEFAULT NULL COMMENT '逻辑删除标识符',
  `create_time` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` decimal(13,0) DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';
```

> 新建人事中历史所有部门信息
```sql
CREATE TABLE `gs_gxjg_all_dept` (
  `etl_jfuuid` varchar(50) NOT NULL COMMENT 'id',
  `bmdm` varchar(2) NOT NULL COMMENT '部门代码',
  `bmmc` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sjbmdm` varchar(2) DEFAULT NULL COMMENT '上级部门代码',
  `fzr` varchar(50) DEFAULT NULL COMMENT '负责人',
  `sort_num` decimal(4,0) DEFAULT NULL COMMENT '排序号',
  `etl_pri` varchar(50) NOT NULL COMMENT '业务主键',
  `etl_is_delete` smallint(1) DEFAULT NULL COMMENT 'gp删除标志位 1：删除 0未删除',
  `etl_is_checked` smallint(1) DEFAULT NULL COMMENT 'gp是否已完成数据质检标志位 1：是 0：否',
  `etl_md5` varchar(50) DEFAULT NULL COMMENT '校验唯一性',
  `etl_key_md5` varchar(50) DEFAULT NULL COMMENT '多主键表使用',
  `etl_check_type` varchar(50) DEFAULT NULL COMMENT '变动类型',
  `etl_check_date` varchar(50) DEFAULT NULL COMMENT '变动时间',
  PRIMARY KEY (`bmdm`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人事中历史所有部门信息';
```

#  20220902
> 修改接口字段表
```sql
ALTER TABLE `gs_common_table_system`.`connector_field` 
ADD COLUMN `dict_id` varchar(32) NULL COMMENT '数据字典id' AFTER `type`;
```

> 修改自定义表单字段
```sql
ALTER TABLE `gs_common_table_system`.`custom_form_field` 
ADD COLUMN `iz_file_upload` decimal(1, 0) NULL COMMENT '是否为附件上传组件' AFTER `field_precision`;
```

> 修改在借记录
```sql
ALTER TABLE `gs_common_table_system`.`gs_library_borrowing` 
MODIFY COLUMN `loan_id` decimal(12) NULL DEFAULT NULL COMMENT '借阅ID' AFTER `etl_jfuuid`;
```

> 新增接口数据
```sql
INSERT INTO `gs_common_table_system`.`connector_info` (`id`, `conn_name`, `conn_url`, `with_params`, `conn_type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('ff80808182fd4bdc0182fd4e0a7f0000', '在借记录列表获取', '/api/v1/studentDataCenter/libraryBorrowingAll', '?userBarcode=${username}', 'get', 0, 1, 1, 0, 1662107126396, 'admin', 1662107148585, 'admin');
```

#  20220905
> 修改接口管理表
```sql
ALTER TABLE `gs_common_table_system`.`connector_info` 
ADD COLUMN `conn_url_page` varchar(500) NULL COMMENT '接口地址（分页）' AFTER `conn_url`;
```

#  20220906
> 新增菜单数据
```sql
INSERT INTO `gs_common_table_system`.`sys_menus` (`id`, `pid`, `menu_name`, `icon`, `first_level_icon`, `comment`, `type`, `action`, `url`, `app_type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('402881eb82f808c60182f84aef210029', '402881446add8b91016ade45d50d0024', '数据字典', 'documentation', 'documentation', NULL, 0, 0, 'DataDictionaryIndex', NULL, 0, 0, 0, 0, 1662023036703, 'admin', 1662023036703, 'admin');
```

> 修改数据字典表
```sql
ALTER TABLE `gs_common_table_system`.`data_dictionary` 
ADD COLUMN `info_id` varchar(32) NULL COMMENT '同步的标准字典维护的id' AFTER `sort_num`;
```

#  20220907
> 修改自定义表单发布对应接口
```sql
ALTER TABLE `gs_common_table_system`.`custom_form_release_conn` 
ADD COLUMN `conn_type` decimal(1) NULL COMMENT '关联接口类型' AFTER `link_name`;
```

#  20220908
> 新增领导角色
```sql
INSERT INTO `gs_common_table_system`.`sys_roles` (`id`, `role_name`, `comment`, `type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('ff808081831af7fc01831b3190c1004f', '分院领导', NULL, 2, 1, 0, 0, 0, 1662608576704, 'admin', 1662608576704, 'admin');
INSERT INTO `gs_common_table_system`.`sys_roles` (`id`, `role_name`, `comment`, `type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('ff808081831beddc01831bf2ee130000', '校级领导', NULL, 2, 1, 0, 0, 0, 1662621249038, 'admin', 1662621249038, 'admin');
```


#  20220909
> 新增数据接口
```sql
INSERT INTO `gs_common_table_system`.`connector_info` (`id`, `conn_name`, `conn_url`, `conn_url_page`, `with_params`, `conn_type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('ff808081832143e4018321458f410002', '教师全部信息列表获取', '/api/v1/dataCenter/teacherAllAll', '/api/v1/dataCenter/teacherAll', NULL, 'get', 0, 1, 0, 0, 1662710550337, 'admin', 1662710550337, 'admin');
INSERT INTO `gs_common_table_system`.`connector_info` (`id`, `conn_name`, `conn_url`, `conn_url_page`, `with_params`, `conn_type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('ff808081832143e4018321453f450000', '教师详细信息列表获取', '/api/v1/dataCenter/teacherDetailAll', '/api/v1/dataCenter/teacherDetail', NULL, 'get', 0, 1, 0, 0, 1662710529859, 'admin', 1662710529859, 'admin');
UPDATE `gs_common_table_system`.`connector_info` SET `conn_name` = '教师基本信息所有列表', `conn_url` = '/api/v1/dataCenter/teacherBaseAll', `conn_url_page` = '/api/v1/dataCenter/teacherBase', `with_params` = '?gh=${username}', `conn_type` = 'get', `sort_num` = 0, `status_num` = 1, `version_num` = 2, `is_delete` = 0, `create_time` = 1658282614760, `create_user` = 'admin', `update_time` = 1662710607583, `update_user` = 'admin' WHERE `id` = 'ff8080818219558e018219589feb0000';
```

> 新增行政管理人员角色
```sql
INSERT INTO `gs_common_table_system`.`sys_roles` (`id`, `role_name`, `comment`, `type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('ff808081831c4963018320feab020033', '行政管理人员', NULL, 2, 1, 0, 0, 0, 1662705904385, 'admin', 1662705904385, 'admin');
```

#  20220916
> 新增数据开放主题管理
```sql
CREATE TABLE `standard_data_theme` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `theme_name` varchar(32) DEFAULT NULL COMMENT '主题名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort_num` decimal(5,0) DEFAULT 0 COMMENT '排序号',
  `status_num` decimal(2,0) DEFAULT NULL COMMENT '状态码',
  `version_num` decimal(13,0) DEFAULT NULL COMMENT '数据版本，乐观锁',
  `is_delete` decimal(1,0) DEFAULT NULL COMMENT '逻辑删除标识符',
  `create_time` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` decimal(13,0) DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据开放主题管理';
```

> 新增数据开放管理
```sql
CREATE TABLE `data_open` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `theme_id` varchar(32) DEFAULT NULL COMMENT '所属主题',
  `data_item_name` varchar(32) DEFAULT NULL COMMENT '数据项名称',
  `conn_id` varchar(32) DEFAULT NULL COMMENT '接口地址',
  `icons` varchar(32) DEFAULT NULL COMMENT '图标',
  `open_roles` varchar(255) DEFAULT NULL COMMENT '开放角色',
  `sort_num` decimal(5,0) DEFAULT 0 COMMENT '排序号',
  `status_num` decimal(2,0) DEFAULT NULL COMMENT '状态码',
  `version_num` decimal(13,0) DEFAULT NULL COMMENT '数据版本，乐观锁',
  `is_delete` decimal(1,0) DEFAULT NULL COMMENT '逻辑删除标识符',
  `create_time` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` decimal(13,0) DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据开放管理';
```

# 20221011
>增加字段
```sql
ALTER TABLE `gs_common_table_system`.`custom_form_release` 
ADD COLUMN `item_dom_id` varchar(50) NULL COMMENT '门户事项档案号,具备事项的唯一性' AFTER `is_flow`;
```

#  20221209
> 修改教师全部信息表主键
```sql
ALTER TABLE `gs_common_table_system`.`gs_teacher_all_info` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`etl_jfuuid`) USING BTREE;
```

> 修改教师详细信息表主键
```sql
ALTER TABLE `gs_common_table_system`.`gs_teacher_detail_info` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`etl_jfuuid`) USING BTREE;
```

> 修改教师基本信息表主键
```sql
ALTER TABLE `gs_common_table_system`.`gs_teacher_base_info` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`etl_jfuuid`) USING BTREE;
```

#20221215
```sql
CREATE TABLE `message_sending_template` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `template_name` varchar(32) DEFAULT NULL COMMENT '模版名称',
  `template_content` text DEFAULT NULL COMMENT '模版内容',
  `matching_key` varchar(50) DEFAULT NULL COMMENT '匹配的key',
  `send_obj` varchar(50) DEFAULT NULL COMMENT '发送的对象（角色）',
  `type` decimal(1,0) DEFAULT NULL COMMENT '类型（1.短信 2.微信消息）',
  `is_open` decimal(1,0) DEFAULT NULL COMMENT '是否开启（0.否 1.是）',
  `sort_num` decimal(5,0) DEFAULT 0 COMMENT '排序号',
  `status_num` decimal(2,0) DEFAULT NULL COMMENT '状态码（0：正常，1：撤销）',
  `version_num` decimal(13,0) DEFAULT NULL COMMENT '数据版本，乐观锁',
  `is_delete` decimal(1,0) DEFAULT NULL COMMENT '逻辑删除标识符',
  `create_time` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` decimal(13,0) DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息发送模版表';
CREATE TABLE `message_sending_data` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `send_user` varchar(32) DEFAULT NULL COMMENT '发送人',
  `receive_gh` varchar(32) DEFAULT NULL COMMENT '接受人工号',
  `receive_phone` varchar(32) DEFAULT NULL COMMENT '接受人手机号',
  `message_content` text DEFAULT NULL COMMENT '消息内容（正文）',
  `belong_template` varchar(32) DEFAULT NULL COMMENT '所属模版',
  `belong_release_id` varchar(32) DEFAULT NULL COMMENT '所属表单发布id',
  `belong_fill_id` varchar(32) DEFAULT NULL COMMENT '所属填报id',
  `send_status` decimal(2,0) DEFAULT NULL COMMENT '发送状态(0-失败,1-成功)',
  `send_date_time` decimal(13,0) DEFAULT NULL COMMENT '发送时间',
  `wechat_user_id` varchar(32) DEFAULT NULL COMMENT '接收人的微信userId',
  `content` text DEFAULT NULL COMMENT '消息完整对象',
  `type` decimal(1,0) DEFAULT NULL COMMENT '消息类型（1.短信 2.微信消息）',
  `sort_num` decimal(5,0) DEFAULT 0 COMMENT '排序号',
  `status_num` decimal(2,0) DEFAULT NULL COMMENT '状态码（0：正常，1：撤销）',
  `version_num` decimal(13,0) DEFAULT NULL COMMENT '数据版本，乐观锁',
  `is_delete` decimal(1,0) DEFAULT NULL COMMENT '逻辑删除标识符',
  `create_time` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` decimal(13,0) DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息发送数据管理表';
```
```sql
INSERT INTO `gs_common_table_system`.`sys_menus` (`id`, `pid`, `menu_name`, `icon`, `first_level_icon`, `comment`, `type`, `action`, `url`, `app_type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('4028818b8514dd8d018514f55ae40004', '4028818b8514dd8d018514f305ce0000', '消息发送数据', 'documentation', 'documentation', NULL, 0, 0, 'MessageSendingDataIndex', NULL, 0, 0, 0, 0, 1671093902050, 'admin', 1671093902050, 'admin');
INSERT INTO `gs_common_table_system`.`sys_menus` (`id`, `pid`, `menu_name`, `icon`, `first_level_icon`, `comment`, `type`, `action`, `url`, `app_type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('4028818b8514dd8d018514f38ba90002', '4028818b8514dd8d018514f305ce0000', '消息发送模版', 'documentation', 'documentation', NULL, 0, 0, 'MessageSendingTemplateIndex', NULL, 0, 0, 0, 0, 1671093783461, 'admin', 1671093783461, 'admin');
INSERT INTO `gs_common_table_system`.`sys_menus` (`id`, `pid`, `menu_name`, `icon`, `first_level_icon`, `comment`, `type`, `action`, `url`, `app_type`, `sort_num`, `status_num`, `version_num`, `is_delete`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('4028818b8514dd8d018514f305ce0000', NULL, '消息发送管理', 'documentation', 'documentation', NULL, 0, 0, NULL, NULL, 0, 0, 0, 0, 1671093749160, 'admin', 1671093749160, 'admin');
```

#20230316
> 新增全部专业基本信息
```sql
CREATE TABLE `gx_gxxs_major_base_infp` (
  `yx_id` varchar(5) DEFAULT NULL COMMENT '学院id',
  `yxmc` varchar(100) DEFAULT NULL COMMENT '学院名称',
  `yxdm` varchar(10) DEFAULT NULL COMMENT '院系代码',
  `id` varchar(4) NOT NULL COMMENT '专业id',
  `zymc` varchar(50) NOT NULL COMMENT '专业名称',
  `zy_dm` varchar(10) NOT NULL COMMENT '专业代码',
  `gbzydm` varchar(8) DEFAULT NULL COMMENT '国标专业代码',
  `xz` varchar(3) DEFAULT NULL COMMENT '学制',
  `etl_pri` varchar(50) NOT NULL COMMENT '业务主键',
  `etl_md5` varchar(50) DEFAULT NULL COMMENT '校验唯一性',
  `etl_key_md5` varchar(50) DEFAULT NULL COMMENT '多主键表使用',
  `etl_check_type` varchar(50) DEFAULT NULL COMMENT '变动类型',
  `etl_check_date` varchar(50) DEFAULT NULL COMMENT '变动时间',
  `create_time` bigint(20) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `is_delete` int(11) DEFAULT NULL,
  `status_num` int(11) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `version_num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`etl_pri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全部专业基本信息';
```
> 新增学生寝室卫生检查
```sql
CREATE TABLE `gs_stu_dorm_health_check` (
  `etl_jfuuid` varchar(50) NOT NULL COMMENT '主键',
  `bedroom_id` varchar(20) DEFAULT NULL COMMENT '寝室id',
  `score` decimal(3,0) DEFAULT NULL COMMENT '分值',
  `bedroom_name` varchar(32) DEFAULT NULL COMMENT '寝室名称',
  `bedroom_lou` varchar(20) DEFAULT NULL COMMENT '寝室楼号',
  `bedroom_floor` varchar(20) DEFAULT NULL COMMENT '寝室楼层',
  `bedroom_area` varchar(20) DEFAULT NULL COMMENT '寝室区域',
  `user_type_name` varchar(10) DEFAULT NULL COMMENT '检查人员类型名称',
  `danger` varchar(2) DEFAULT NULL COMMENT '有无违禁品',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `check_date` varchar(20) DEFAULT NULL COMMENT '检查日期',
  `check_user` varchar(50) DEFAULT NULL COMMENT '检查人员姓名',
  `check_user_code` varchar(20) DEFAULT NULL COMMENT '检查人员工号',
  `level_type` varchar(4) DEFAULT NULL COMMENT '等级',
  `etl_pri` varchar(50) DEFAULT NULL COMMENT '业务主键',
  `etl_is_delete` smallint(1) DEFAULT NULL COMMENT 'gp删除标志位 1：删除 0未删除',
  `etl_is_checked` smallint(1) DEFAULT NULL COMMENT 'gp是否已完成数据质检标志位 1：是 0：否',
  `etl_md5` varchar(50) DEFAULT NULL COMMENT '校验唯一性',
  `etl_key_md5` varchar(50) DEFAULT NULL COMMENT '多主键表使用',
  `etl_check_type` varchar(50) DEFAULT NULL COMMENT '变动类型',
  `etl_check_date` varchar(50) DEFAULT NULL COMMENT '变动时间',
  PRIMARY KEY (`etl_jfuuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生寝室卫生检查';
```

> 新增学生寝室晚归签到
```sql
CREATE TABLE `gs_stu_dorm_night_sign` (
  `etl_jfuuid` varchar(50) NOT NULL COMMENT '主键',
  `xh` varchar(40) NOT NULL COMMENT '学号',
  `type` varchar(20) DEFAULT NULL COMMENT '签到类别(3:晚归,4:夜不归宿)',
  `check_time` varchar(20) DEFAULT NULL COMMENT '夜检日期',
  `way` varchar(20) DEFAULT NULL COMMENT '生成方式(1:夜检员,2:夜检学生扫码,3:自己扫二维码晚归,4:系统自动生成,5:自己登记)',
  `late_time` varchar(20) DEFAULT NULL COMMENT '晚归时间',
  `check_stu` varchar(255) DEFAULT NULL COMMENT '检查人员姓名',
  `check_stu_code` varchar(255) DEFAULT NULL COMMENT '检查人员学号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  `etl_pri` varchar(50) DEFAULT NULL COMMENT '业务主键',
  `etl_is_delete` smallint(1) DEFAULT NULL COMMENT 'gp删除标志位 1：删除 0未删除',
  `etl_is_checked` smallint(1) DEFAULT NULL COMMENT 'gp是否已完成数据质检标志位 1：是 0：否',
  `etl_md5` varchar(50) DEFAULT NULL COMMENT '校验唯一性',
  `etl_key_md5` varchar(50) DEFAULT NULL COMMENT '多主键表使用',
  `etl_check_type` varchar(50) DEFAULT NULL COMMENT '变动类型',
  `etl_check_date` varchar(50) DEFAULT NULL COMMENT '变动时间',
  PRIMARY KEY (`etl_jfuuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生寝室晚归签到';
```

#20230317
> 新增学生家长/老师留言
```sql
CREATE TABLE `gs_stu_leave_msg` (
  `id` varchar(32) NOT NULL COMMENT '流水号',
  `code` varchar(40) DEFAULT NULL COMMENT '工号/学号',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `type` char(1) DEFAULT NULL COMMENT '类别，0-班主任 1-辅导员 2-家长',
  `leave_time` varchar(32) DEFAULT NULL COMMENT '留言时间',
  `msg` text DEFAULT NULL COMMENT '留言内容',
  `about_stu_code` varchar(32) NOT NULL COMMENT '关于谁的留言,学号',
  `bzr_read_or_not` char(1) DEFAULT '0' COMMENT '班主任已读未读，0-未读，1-已读',
  `fdy_read_or_not` char(1) DEFAULT '0' COMMENT '辅导员已读未读，0-未读，1-已读',
  `parent_read_or_not` char(1) DEFAULT '0' COMMENT '家长已读未读，0-未读，1-已读',
  `delete_flag` char(1) DEFAULT NULL COMMENT '删除标志,0-未删 1-已删',
  `remark` varchar(255) DEFAULT NULL COMMENT '依据，解决前端翻页数据重复',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生家长/教师留言';
```

#20230321
> 新增学生-家长绑定历史信息
```sql
CREATE TABLE `gs_stu_parents_bind_history` (
  `id` varchar(32) NOT NULL COMMENT '流水号',
  `stu_code` varchar(32) DEFAULT NULL COMMENT '学生学号',
  `parent_name` varchar(32) DEFAULT NULL COMMENT '家长姓名',
  `parent_phone` varchar(32) DEFAULT NULL COMMENT '家长手机号',
  `relation` varchar(32) DEFAULT NULL COMMENT '与该学生关系',
  `open_id` varchar(32) DEFAULT NULL COMMENT '微信唯一验证码',
  `bind_type` char(1) DEFAULT NULL COMMENT '绑定用户方式，0-游客绑定 1-已有绑定',
  `delete_flag` char(1) DEFAULT NULL COMMENT '删除标志,0-未删 1-已删',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生-家长绑定历史信息';
```

> 新增学生家庭联系方式
```sql
CREATE TABLE `gs_gxxs_stu_family_contact` (
  `student_code` varchar(40) NOT NULL COMMENT '学号',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `home_ad` longtext DEFAULT NULL COMMENT '家庭住址',
  `relation` varchar(255) DEFAULT NULL COMMENT '与本人关系',
  `parent_name` varchar(255) DEFAULT NULL COMMENT '家长姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `xl` varchar(255) DEFAULT NULL COMMENT '学历',
  `zy` varchar(255) DEFAULT NULL COMMENT '职业',
  `dw` varchar(255) DEFAULT NULL COMMENT '单位',
  `dwdz` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `etl_pri` varchar(50) NOT NULL COMMENT '业务主键',
  `etl_md5` varchar(50) DEFAULT NULL COMMENT '校验唯一性',
  `etl_key_md5` varchar(50) DEFAULT NULL COMMENT '多主键表使用',
  `etl_check_type` varchar(50) DEFAULT NULL COMMENT '变动类型',
  `etl_check_date` varchar(50) DEFAULT NULL COMMENT '变动时间',
  `etl_jfuuid` varchar(255) NOT NULL,
  `etl_is_checked` int(11) DEFAULT NULL,
  `etl_is_delete` int(11) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `father_phone` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `mother_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_code`,`etl_pri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生家庭联系方式';
```

#20230329
> 新增标签大类
```sql
CREATE TABLE `gs_stu_tag_major_item` (
  `id` varchar(32) NOT NULL COMMENT '流水号，标签大类代码',
  `tag_major_item` varchar(255) DEFAULT NULL COMMENT '标签大类中文',
  `delete_flag` char(1) DEFAULT '0' COMMENT '删除标志,0-未删 1-已删',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_by` int(11) DEFAULT 0 COMMENT '排序',
  `create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生标签大类';
```

> 新增学生标签库
```sql
CREATE TABLE `gs_stu_tag_library` (
  `id` varchar(32) NOT NULL COMMENT '流水号，标签代码',
  `tag_major_item_code` varchar(32) DEFAULT NULL COMMENT '标签大类代码',
  `tag_major_item` varchar(255) DEFAULT NULL COMMENT '标签大类中文',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名字',
  `tag_evaluation_zw` varchar(500) DEFAULT NULL COMMENT '标签评定中文介绍',
  `tag_evaluation_sql` text DEFAULT NULL COMMENT '标签评定 sql',
  `delete_flag` char(1) DEFAULT '0' COMMENT '删除标志,0-未删 1-已删',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_by` int(11) DEFAULT 0 COMMENT '排序',
  `create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生标签库';
```

> 新增学生标签关系表
```sql
CREATE TABLE `gs_stu_tag_relation` (
  `id` varchar(32) NOT NULL COMMENT '流水号',
  `stu_code` varchar(32) DEFAULT NULL COMMENT '学号',
  `tag_id` varchar(32) DEFAULT NULL COMMENT '标签 id',
  `delete_flag` char(1) DEFAULT '0' COMMENT '删除标志,0-未删 1-已删',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_by` int(11) DEFAULT 0 COMMENT '排序',
  `create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生标签关系表';
```

#20230404
> 新增数据使用参照，快照系统取数依据
```sql
CREATE TABLE `gs_data_use_reference` (
  `id` varchar(32) NOT NULL COMMENT '流水号',
  `template_name` varchar(255) DEFAULT NULL COMMENT '数据模板名称',
  `start_time` varchar(32) DEFAULT NULL COMMENT '起始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '截止时间',
  `bjdm` text DEFAULT NULL COMMENT '班级代码',
  `bjmc` text DEFAULT NULL COMMENT '班级名称',
  `xn` varchar(32) DEFAULT NULL COMMENT '学年',
  `xq` varchar(32) DEFAULT NULL COMMENT '学期',
  `status` char(1) DEFAULT NULL COMMENT '是否启用，0-未启用 1-已启用',
  `delete_flag` char(1) DEFAULT '0' COMMENT '删除标志,0-未删 1-已删',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_by` int(11) DEFAULT 0 COMMENT '排序',
  `create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='数据使用参照，快照系统取数依据';
```
