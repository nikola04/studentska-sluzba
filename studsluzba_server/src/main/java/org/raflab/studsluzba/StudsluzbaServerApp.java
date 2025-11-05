package org.raflab.studsluzba;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication(scanBasePackages = {"org.raflab"})
public class StudsluzbaServerApp {

	public static void main(String[] args) {
		SpringApplication.run(StudsluzbaServerApp.class, args);
	}

//	@Bean
//	ApplicationRunner h2Reporter(Environment env, DataSource dataSource) {
//		return args -> {
//			String url = "<unknown>";
//			try (Connection c = dataSource.getConnection()) {
//				url = c.getMetaData().getURL();
//			} catch (Exception ignored) {}
//
//			boolean isH2 = url != null && url.startsWith("jdbc:h2");
//			String port = env.getProperty("server.port", "8090");
//			String ctx  = env.getProperty("server.servlet.context-path", "");
//			boolean h2Console = Boolean.parseBoolean(
//					env.getProperty("spring.h2.console.enabled", "false")
//			);
//			String h2Path = env.getProperty("spring.h2.console.path", "/h2");
//
//			if (isH2) {
//				System.out.println("=== H2 ACTIVE ===================================================");
//				System.out.println("H2 JDBC URL: " + url);
//				System.out.println("H2 username: " + env.getProperty("spring.datasource.username", "sa"));
//				System.out.println("H2 password: " + env.getProperty("spring.datasource.password", ""));
//				if (h2Console) {
//					System.out.println("H2 console:  http://localhost:" + port + ctx + h2Path);
//					System.out.println("Tip: In the console, set JDBC URL to the value above.");
//				}
//				System.out.println("================================================================");
//			} else {
//				System.out.println("[DataSource] Connected to: " + url);
//			}
//		};
//	}
}
