# Repository Guidelines

## Project Structure & Module Organization
本仓库是 `blog-api` 后端服务（Spring Boot 2.2.7, Java 8）。核心代码位于 `src/main/java/top/naccl`，按分层组织：
- `controller` 与 `controller/admin`：前台与后台 REST 接口
- `service` 与 `service/impl`：业务逻辑与实现
- `mapper`：MyBatis Mapper 接口；对应 XML 在 `src/main/resources/mapper`
- `entity`、`model/dto`、`model/vo`：实体与传输模型
- `config`、`interceptor`、`aspect`、`task`：安全、AOP、拦截器与定时任务

资源文件在 `src/main/resources`（如 `application.yml`、`logback-spring.xml`、SQL 初始化脚本 `docs/nblog.sql`）。测试位于 `src/test/java`。

## Build, Test, and Development Commands
- `mvn clean package`：清理并构建可部署包（默认会执行测试）
- `mvn test`：运行单元/集成测试
- `mvn spring-boot:run`：本地启动服务（默认端口 `8090`）

建议在提交前至少执行 `mvn test`；改动配置或依赖后执行 `mvn clean package` 做完整校验。

## Coding Style & Naming Conventions
遵循 Java 常规风格与现有代码约定：
- 包名小写（如 `top.naccl.service`），类名 `PascalCase`，方法/字段 `camelCase`
- 控制器以 `*Controller` 结尾，服务接口以 `*Service`，实现以 `*ServiceImpl`
- Mapper 接口与 XML 同名（如 `BlogMapper.java` / `BlogMapper.xml`）
- 保持与现有文件一致的缩进与 import 风格；避免一次提交混入无关格式化改动

## Testing Guidelines
测试框架为 `spring-boot-starter-test`（JUnit 5）。新增测试放在 `src/test/java`，类名建议以 `*Tests` 结尾。优先覆盖：
- 关键业务分支（权限、参数校验、状态变更）
- Mapper 查询/更新的边界场景

运行示例：`mvn test -Dtest=BlogApiApplicationTests`。

## Commit & Pull Request Guidelines
现有历史以简短祈使句为主（如“初始化”“删除无关文件”“Delete ...”）。建议统一为：`<动词><范围>`，例如：
- `feat: 新增评论审核接口`
- `fix: 修复JWT过期判定`
- `refactor: 清理VisitLog查询逻辑`

PR 应包含：变更目的、主要改动点、测试结果（命令与结论）、关联 issue。若修改接口或配置，请附示例请求/响应或配置迁移说明。

## Security & Configuration Tips
- `application.yml` 仅保留通用配置；敏感信息放环境变量或私有配置文件
- 不要提交密钥、令牌、生产数据库连接信息
- 涉及认证（JWT/Security）与管理端接口（`/admin/**`）的改动需重点自测
