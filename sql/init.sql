                                                              /*初始化系统sql*/
create table sys_resources
(
  id          bigint identity
    constraint sys_resources_pk
      primary key nonclustered,
  name        varchar(50)  not null,
  description varchar(500),
  http_path   varchar(200) not null,
  is_deleted  bit          not null,
  create_time datetime2    not null,
  update_time datetime2    not null
)
go

exec sp_addextendedproperty 'MS_Description', '资源表', 'SCHEMA', 'sde', 'TABLE', 'sys_resources'
go

exec sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', 'sde', 'TABLE', 'sys_resources', 'COLUMN', 'name'
go

exec sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', 'sde', 'TABLE', 'sys_resources', 'COLUMN', 'description'
go

exec sp_addextendedproperty 'MS_Description', '访问路径', 'SCHEMA', 'sde', 'TABLE', 'sys_resources', 'COLUMN', 'http_path'
go

create table sys_roles
(
  id          bigint identity
    constraint sys_role_pk
      primary key nonclustered,
  role_name   varchar(20),
  description varchar(500),
  is_deleted  bit       not null,
  create_time datetime2 not null,
  update_time datetime2 not null
)
go

exec sp_addextendedproperty 'MS_Description', '角色表', 'SCHEMA', 'sde', 'TABLE', 'sys_roles'
go

exec sp_addextendedproperty 'MS_Description', '角色名称', 'SCHEMA', 'sde', 'TABLE', 'sys_roles', 'COLUMN', 'role_name'
go

exec sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', 'sde', 'TABLE', 'sys_roles', 'COLUMN', 'description'
go

create table sys_roles_resources
(
  id               bigint identity
    constraint sys_roles_resources_pk
      primary key nonclustered,
  sys_roles_id     bigint not null,
  sys_resources_id bigint not null
)
go

exec sp_addextendedproperty 'MS_Description', '角色-资源 关联表', 'SCHEMA', 'sde', 'TABLE', 'sys_roles_resources'
go

exec sp_addextendedproperty 'MS_Description', '角色表主键', 'SCHEMA', 'sde', 'TABLE', 'sys_roles_resources', 'COLUMN',
     'sys_roles_id'
go

exec sp_addextendedproperty 'MS_Description', '资源表主键', 'SCHEMA', 'sde', 'TABLE', 'sys_roles_resources', 'COLUMN',
     'sys_resources_id'
go

create table sys_user
(
  id             bigint identity
    constraint sys_user_pk
      primary key nonclustered,
  name           varchar(20),
  password       varchar(200) not null,
  mobile         varchar(11),
  email          varchar(50),
  position       varchar(20)  not null,
  area_code      varchar(30),
  birthday       datetime2,
  education      varchar(20),
  gender         int,
  profession     varchar(100),
  photo          varchar(100),
  college        varchar(20),
  urgent_person  varchar(20),
  urgent_contact varchar(11),
  dept_id        bigint,
  is_deleted     bit          not null,
  create_time    datetime2    not null,
  update_time    datetime2    not null,
)
go

exec sp_addextendedproperty 'MS_Description', '用户表', 'SCHEMA', 'sde', 'TABLE', 'sys_user'
go

exec sp_addextendedproperty 'MS_Description', '用户名', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'name'
go

exec sp_addextendedproperty 'MS_Description', '手机号', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'mobile'
go

exec sp_addextendedproperty 'MS_Description', '邮箱地址', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'email'
go

exec sp_addextendedproperty 'MS_Description', '职务', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'position'
go

exec sp_addextendedproperty 'MS_Description', '行政区划编码', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'area_code'
go

exec sp_addextendedproperty 'MS_Description', '生日', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'birthday'
go

exec sp_addextendedproperty 'MS_Description', '学历', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'education'
go

exec sp_addextendedproperty 'MS_Description', '性别1：男2：女3：不男不女', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'gender'
go

exec sp_addextendedproperty 'MS_Description', '专业', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'profession'
go

exec sp_addextendedproperty 'MS_Description', '照片链接', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'photo'
go

exec sp_addextendedproperty 'MS_Description', '毕业学院', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'college'
go

exec sp_addextendedproperty 'MS_Description', '紧急联系人', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'urgent_person'
go

exec sp_addextendedproperty 'MS_Description', '紧急联系人联系方式', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN',
     'urgent_contact'
go

exec sp_addextendedproperty 'MS_Description', '关联部门表', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'dept_id'
go

exec sp_addextendedproperty 'MS_Description', '用户密码', 'SCHEMA', 'sde', 'TABLE', 'sys_user', 'COLUMN', 'password'
go

create unique index sys_user_name_uindex
  on sys_user (name)
go

create unique index sys_user_mobile_uindex
  on sys_user (mobile)
go

create unique index sys_user_email_uindex
  on sys_user (email)
go

create table sys_user_roles
(
  id           bigint identity
    constraint sys_user_roles_pk
      primary key nonclustered,
  sys_user_id  bigint not null,
  sys_roles_id bigint not null
)
go

exec sp_addextendedproperty 'MS_Description', '用户-角色 关联表', 'SCHEMA', 'sde', 'TABLE', 'sys_user_roles'
go

exec sp_addextendedproperty 'MS_Description', '用户表主键', 'SCHEMA', 'sde', 'TABLE', 'sys_user_roles', 'COLUMN',
     'sys_user_id'
go

exec sp_addextendedproperty 'MS_Description', '角色表主键', 'SCHEMA', 'sde', 'TABLE', 'sys_user_roles', 'COLUMN',
     'sys_roles_id'
go

