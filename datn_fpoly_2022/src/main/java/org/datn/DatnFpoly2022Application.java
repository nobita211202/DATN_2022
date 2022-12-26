package org.datn;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DatnFpoly2022Application {
	@Value("${socket.server.port}")
	int port ;
	@Value("${socket.server.host}")
	String host ;
	public static void main(String[] args) {
		SpringApplication.run(DatnFpoly2022Application.class, args);
	}

	@Bean
	public SocketIOServer createSocketServer(){
		Configuration config = new Configuration();
		config.setHostname(host);
		config.setPort(port);
		SocketIOServer server = new SocketIOServer(config);
		log.info("Socket server started at port: {}", port);
		server.start();
		return server;
	}

}
