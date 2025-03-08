package com.example.emp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库初始化工具
 * 在应用启动时自动执行SQL脚本，创建必要的表结构
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        logger.info("开始初始化数据库...");
        
        // 执行初始化SQL脚本
        executeSqlScript("db/01_init.sql");
        executeSqlScript("db/02_user.sql");
        executeSqlScript("db/02_data.sql");
        
        logger.info("数据库初始化完成");
    }
    
    /**
     * 执行SQL脚本文件
     * @param scriptPath 脚本路径（相对于classpath）
     */
    private void executeSqlScript(String scriptPath) {
        try {
            logger.info("执行SQL脚本: {}", scriptPath);
            
            // 读取SQL脚本文件
            ClassPathResource resource = new ClassPathResource(scriptPath);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            
            StringBuilder sqlBuilder = new StringBuilder();
            List<String> sqlStatements = new ArrayList<>();
            String line;
            
            // 解析SQL语句
            while ((line = reader.readLine()) != null) {
                // 忽略注释和空行
                if (line.trim().startsWith("--") || line.trim().isEmpty()) {
                    continue;
                }
                
                sqlBuilder.append(line).append(" ");
                
                // 如果是SQL语句结束，则添加到列表中
                if (line.trim().endsWith(";")) {
                    sqlStatements.add(sqlBuilder.toString());
                    sqlBuilder.setLength(0);
                }
            }
            
            // 执行SQL语句
            for (String sql : sqlStatements) {
                try {
                    // 跳过USE语句，因为数据源已经指定了数据库
                    if (sql.trim().toUpperCase().startsWith("USE")) {
                        continue;
                    }
                    
                    jdbcTemplate.execute(sql);
                } catch (Exception e) {
                    logger.error("执行SQL语句失败: {}", sql, e);
                    // 继续执行下一条SQL语句，不中断整个过程
                }
            }
            
            reader.close();
            logger.info("SQL脚本执行完成: {}", scriptPath);
        } catch (Exception e) {
            logger.error("执行SQL脚本失败: {}", scriptPath, e);
        }
    }
}