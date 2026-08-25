# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

这是一个基于 Spring Boot 2.2.7 的个人博客后端 API 项目，使用 MyBatis + MySQL 架构。

## 构建和运行

**启动应用**
```bash
mvn spring-boot:run
```

**打包**
```bash
mvn clean package
```

**运行测试**
```bash
mvn test
```

**切换环境**
- 修改 `src/main/resources/application.yml` 中的 `spring.profiles.active` 为 `dev` 或 `prod`
- 默认端口：8090

## 架构说明

### 分层结构
- **controller**: REST API 控制器，分为前台接口和 `/admin` 后台管理接口
- **service/impl**: 业务逻辑层
- **mapper**: MyBatis 数据访问层，XML 映射文件位于 `src/main/resources/mapper/`
- **entity**: 数据库实体类
- **model/dto**: 数据传输对象
- **config**: 配置类，包含 Spring Security、JWT、Redis 等配置

### 核心功能模块
- **认证授权**: 基于 JWT 的无状态认证，Spring Security 配置在 `SecurityConfig.java`
  - `/admin/login` 登录接口通过 `JwtLoginFilter` 处理
  - 所有 `/admin/**` 请求需要 JWT 验证（通过 `JwtFilter`）
  - 管理员角色：admin，访客角色：visitor
- **日志系统**: 使用 AOP 切面记录操作日志、访问日志和异常日志
  - `@OperationLogger`: 操作日志注解
  - `@VisitLogger`: 访问日志注解
  - `@AccessLimit`: 访问限流注解
- **定时任务**: Quartz 调度框架，任务类在 `task/` 包下
- **缓存**: Redis 用于缓存和访客统计
- **文件上传**: 支持又拍云存储

### 关键配置
- **数据库**: MySQL，配置在 `application-{profile}.yml`
- **MyBatis**: mapper XML 路径为 `classpath:mapper/*.xml`，开启驼峰命名转换
- **JWT**: token 配置在 `token.secretKey` 和 `token.expireTime`
- **评论通知**: 支持邮件和 Telegram 通知，配置在 `comment.notify.channel`

### 依赖说明
- Spring Security + JWT 认证
- MyBatis + PageHelper 分页
- Redis 缓存
- Quartz 定时任务
- CommonMark Markdown 解析
- ip2region IP 地址解析
- yauaa User-Agent 解析
