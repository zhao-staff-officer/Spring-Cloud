package com.cloud.staff.mybatisplus.crew;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：
 * @date 2022/6/22 10:26
 **/
public class Test {

    public static void main(String[] args) {
        // 获取桌面目录作为文档存放目录, 指定生成的文件名称
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        String fileName = "数据库设计文档";
        // 生成的文件类型：HTML-> .html，WORD-> .doc、MD-> .md
        EngineFileType fileType = EngineFileType.WORD;

        // 数据源也可以从 Spring 容器中获取，或者通过 jdbcTemplate 获取.
        // screw 1.0.5 版本时, cn.smallbun.screw.core.query.AbstractDatabaseQuery.getSchema 中会强转为 HikariDataSource
        // 所以只支持 HikariDataSource 数据源(Spring boot 默认 HikariCP 作为数据源.)
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://192.168.138.107:3307/productdb?serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);

        // 文件生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件存放的目录
                .fileOutputDir(homeDirectory.getAbsolutePath())
                // 生成完成是否打开输出目录
                .openOutputDir(true)
                // 生成文件类型：HTML-> .html，WORD-> .doc、MD-> .md
                .fileType(fileType)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker)
                // 自定义文件名称
                .fileName(fileName).build();

        // 忽略指定名称的表
        ArrayList<String> ignoreTableName = new ArrayList<>();
//        ignoreTableName.add("test_user");
//        ignoreTableName.add("test_group");
        // 忽略指定名称前缀的表
        ArrayList<String> ignorePrefix = new ArrayList<>();
//        ignorePrefix.add("test_");
        // 忽略指定名称后缀的表
        ArrayList<String> ignoreSuffix = new ArrayList<>();
//        ignoreSuffix.add("_test");
//        ignoreSuffix.add("_backup");

        // 数据处理配置
        ProcessConfig processConfig = ProcessConfig.builder()
                // 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                // 根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                // 根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                // 根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                // 忽略指定名称的表
                .ignoreTableName(ignoreTableName)
                // 忽略指定名称前缀的表
                .ignoreTablePrefix(ignorePrefix)
                // 忽略指定名称后缀的表
                .ignoreTableSuffix(ignoreSuffix).build();

        // Screw 配置入口
        Configuration config = Configuration.builder()
                // 版本
                .version("1.0.0")
                // 描述
                .description("数据库设计文档生成")
                // 数据源(可以自己单独配置，也可以直接从应用中获取)
                .dataSource(dataSource)
                // 文档生成配置
                .engineConfig(engineConfig)
                // 数据处理配置
                .produceConfig(processConfig)
                .build();
        // 执行生成文档
        new DocumentationExecute(config).execute();

        // 文档文件全路径, 如 E:\桌面\数据库设计文档.html
        String fullPath = homeDirectory + File.separator + fileName + fileType.getFileSuffix();
    }
}
