# RBAC 权限模型  基于角色的权限控制

# 用户表
# 张三

# 权限表
# 删除 新增 修改


# 角色表
# 图书管理员   删除 查看 修改 权限
# 图书馆会员 借阅人  只有查看权限

# 角色权限关联表
#   角色id   权限id
#   一个角色可有多个权限



# 用户角色关联表  多对多
# 一个用户可以有多个角色
#    用户id   角色id
#  一个用户可以有多个角色， 一个角色也可以给多个用户


DROP TABLE IF EXISTS `sys_user`;
# 用户表
CREATE TABLE `sys_user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `username` varchar(50) NOT NULL COMMENT '用户名',
                        `password` varchar(100) NOT NULL COMMENT '密码',
                        `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
                        `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                        `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
                        `status` char(1) default '0' COMMENT '用户状态（0正常 1停用）',
                        `gender` char(1) default null  COMMENT '性别（0男 1女 2未知）',
                        `avatar` varchar(128) default null  COMMENT '头像',
                        `user_type` char(1) default '1'  COMMENT '用户类型（0管理员 1普通用户）',
                        `del_flag` int(1) default '0' COMMENT '删除标记（0正常 1删除）',
                        `create_by` bigint(200) DEFAULT NULL COMMENT '创建人',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_by` bigint(200) DEFAULT NULL COMMENT '更新人',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB   AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入用户数据示例1
INSERT INTO sys_user (id, username, password, nickname, email, phone, status, gender, avatar, user_type, del_flag, create_by, create_time, update_by, update_time)
VALUES (1, 'user1', 'password123', 'User One', 'user1@example.com', '1234567890', '0', '0', 'avatar1.jpg', '1', '0', '1', NOW(), '1', NOW());

-- 插入用户数据示例2
INSERT INTO sys_user (id, username, password, nickname, email, phone, status, gender, avatar, user_type, del_flag, create_by, create_time, update_by, update_time)
VALUES (2, 'user2', 'password456', 'User Two', 'user2@example.com', '9876543210', '0', '1', 'avatar2.jpg', '1', '0', '1', NOW(), '1', NOW());

-- 插入用户数据示例3
INSERT INTO sys_user (id, username, password, nickname, email, phone, status, gender, avatar, user_type, del_flag, create_by, create_time, update_by, update_time)
VALUES (3, 'admin', '229ecef7cbc769e05169393b63f141ee7691fae0bd5dddfbdc3a43228842b61954924e1b58c7f367', 'Admin', 'admin@example.com', '5551234567', '0', '0', 'avatar_admin.jpg', '0', '0', '1', NOW(), '1', NOW());

-- 插入用户数据示例4
INSERT INTO sys_user (id, username, password, nickname, email, phone, status, gender, avatar, user_type, del_flag, create_by, create_time, update_by, update_time)
VALUES (4, 'test_user', 'test123', 'Test User', 'test@example.com', '1231231234', '0', '2', NULL, '1', '0', '1', NOW(), '1', NOW());

DROP TABLE IF EXISTS `sys_role`;
# 角色表
CREATE TABLE `sys_role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                        `name` varchar(128)  NULL COMMENT '角色名称',
                        `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
                        `status` char(1) default '0' COMMENT '角色状态（0正常 1停用）',
                        `del_flag` int(1) default '0' COMMENT '删除标记（0正常 1删除）',
                        `description` varchar(255) DEFAULT NULL COMMENT '描述',
                        `create_by` bigint(200) DEFAULT NULL COMMENT '创建人',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_by` bigint(200) DEFAULT NULL COMMENT '更新人',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 插入角色数据示例1
INSERT INTO sys_role (id, name, role_key, status, del_flag, description, create_by, create_time, update_by, update_time)
VALUES (1, '管理员', 'admin', '0', '0', '管理员角色', '1', NOW(), '1', NOW());

-- 插入角色数据示例2
INSERT INTO sys_role (id, name, role_key, status, del_flag, description, create_by, create_time, update_by, update_time)
VALUES (2, '普通用户', 'user', '0', '0', '普通用户角色', '1', NOW(), '1', NOW());

-- 插入角色数据示例3
INSERT INTO sys_role (id, name, role_key, status, del_flag, description, create_by, create_time, update_by, update_time)
VALUES (3, '审计员', 'auditor', '0', '0', '审计员角色', '1', NOW(), '1', NOW());

-- 插入角色数据示例4
INSERT INTO sys_role (id, name, role_key, status, del_flag, description, create_by, create_time, update_by, update_time)
VALUES (4, '超级管理员', 'super_admin', '0', '0', '超级管理员角色', '1', NOW(), '1', NOW());


DROP TABLE IF EXISTS `sys_user_role`;
# 用户角色关联表
CREATE TABLE `sys_user_role` (
                             `user_id` bigint(200) NOT NULL COMMENT '用户ID',
                             `role_id` bigint(200) NOT NULL COMMENT '角色ID',
                             PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';
-- 插入用户角色关联数据示例1
INSERT INTO sys_user_role (user_id, role_id)VALUES (3, 1);
-- 插入用户角色关联数据示例2
INSERT INTO sys_user_role (user_id, role_id)VALUES (3, 4);
-- 插入用户角色关联数据示例3
INSERT INTO sys_user_role (user_id, role_id)VALUES (2, 2);
-- 插入用户角色关联数据示例4
INSERT INTO sys_user_role (user_id, role_id)VALUES (3, 1);


DROP TABLE IF EXISTS `sys_menu`;

# 权限菜单表
CREATE TABLE `sys_menu` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                              `menu_name` varchar(100) NOT NULL default 'NULL' COMMENT '权限菜单名称',
                              `path` varchar(200)  NULL COMMENT '路由地址',
                              `component` varchar(255)  default NULL COMMENT '组件路径',
                              `visible` char(1) default '0' COMMENT '菜单状态（0显示 1隐藏）',
                              `status` char(1) default '0' COMMENT '菜单状态（0正常 1停用）',
                              `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
                              `icon` varchar(255) DEFAULT '#' COMMENT '菜单图标',
                              `del_flag` int(1) default '0' COMMENT '删除标记（0正常 1删除）',
                              `create_by` bigint(200) DEFAULT NULL COMMENT '创建人',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` bigint(200) DEFAULT NULL COMMENT '更新人',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200  DEFAULT CHARSET=utf8mb4 COMMENT='权限菜单表';

-- 插入权限菜单数据示例1
INSERT INTO sys_menu (id, menu_name, path, component, visible, status, perms, icon, del_flag, create_by, create_time, update_by, update_time)
VALUES (1, '系统管理', '/system', 'System', '0', '0', 'system:manage', 'dashboard', '0', '1', NOW(), '1', NOW());

-- 插入权限菜单数据示例2
INSERT INTO sys_menu (id, menu_name, path, component, visible, status, perms, icon, del_flag, create_by, create_time, update_by, update_time)
VALUES (2, '用户管理', '/system/user', 'User', '0', '0', 'system:user:manage', 'user', '0', '1', NOW(), '1', NOW());

-- 插入权限菜单数据示例3
INSERT INTO sys_menu (id, menu_name, path, component, visible, status, perms, icon, del_flag, create_by, create_time, update_by, update_time)
VALUES (3, '角色管理', '/system/role', 'Role', '0', '0', 'system:role:manage', 'team', '0', '1', NOW(), '1', NOW());

-- 插入权限菜单数据示例4
INSERT INTO sys_menu (id, menu_name, path, component, visible, status, perms, icon, del_flag, create_by, create_time, update_by, update_time)
VALUES (4, '菜单管理', '/system/menu', 'Menu', '0', '0', 'system:menu:manage', 'menu', '0', '1', NOW(), '1', NOW());


DROP TABLE IF EXISTS `sys_menu_role`;
# 角色权限菜单关联表
CREATE TABLE `sys_menu_role` (
                                   `role_id` bigint(200) NOT NULL COMMENT '角色ID',
                                   `menu_id` bigint(200) NOT NULL COMMENT '菜单权限ID',
                                   PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限菜单关联表';

-- 插入角色权限菜单关联数据示例1
INSERT INTO sys_menu_role (role_id, menu_id)VALUES (1, 1);
-- 插入角色权限菜单关联数据示例2
INSERT INTO sys_menu_role (role_id, menu_id)VALUES (1, 2);
-- 超级管理员角色权限
INSERT INTO sys_menu_role (role_id, menu_id)VALUES (4, 1);
INSERT INTO sys_menu_role (role_id, menu_id)VALUES (4, 2);
INSERT INTO sys_menu_role (role_id, menu_id)VALUES (4, 3);
INSERT INTO sys_menu_role (role_id, menu_id)VALUES (4, 4);
-- 插入角色权限菜单关联数据示例3
INSERT INTO sys_menu_role (role_id, menu_id)VALUES (2, 2);
-- 插入角色权限菜单关联数据示例4
INSERT INTO sys_menu_role (role_id, menu_id) VALUES (3, 3);

# 表说明：
# 用户表 (user)：存储系统用户信息，包括用户名、密码、昵称、邮箱等。
# 角色表 (role)：定义系统中的角色，包括角色名称和描述。
# 用户角色关联表 (user_role)：用于表示用户和角色之间的关联关系，一个用户可以拥有多个角色。
# 权限表 (permission)：定义系统中的权限，包括权限名称和描述。
# 角色权限关联表 (role_permission)：用于表示角色和权限之间的关联关系，一个角色可以拥有多个权限。




