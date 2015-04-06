package com.marketour.services;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class MarkeTourServices {

	public static void main(String[] args) throws Exception {

		String webappDirLocation = "src/main/webapp/";

		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}

		WebAppContext root = new WebAppContext();
		root.setContextPath("/");
		root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
		root.setResourceBase(webappDirLocation);
		root.setParentLoaderPriority(true);

		Server server = new Server(Integer.valueOf(webPort));
		server.setHandler(root);
		server.start();
		server.join();
	}
}
