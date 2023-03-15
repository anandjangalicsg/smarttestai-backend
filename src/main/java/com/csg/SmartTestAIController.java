package com.csg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/smarttestai-api")
@CrossOrigin("*")

@Slf4j
public class SmartTestAIController {
	
	@GetMapping("/health")
	public String health() {
		return "success";
	}

	@GetMapping("/fileCheck")
	public Response fileIsExist() {
		log.info("fileCheck() Started");
		Response res = new Response();
		String filePath="D:/Anand/Node/SmartTest-AI/AutomationReports/dashboard.html"; //Ravi systenm path
		log.info(filePath);
		File file = new File(filePath);

		if (file.exists()) {
			log.info("file is avialable");
			res.setStatus("success");
		} else {
			log.info("file is not avialable");
			res.setStatus("fail");

		}
		log.info("fileCheck() Completed");
		return res;
	}

	@GetMapping("/testExecution")
	public void testExecution() {
		log.info("testExecution started");
		String folderPath = "D:/Anand/Node";
		log.info("File path :"+folderPath);
		String[] command = { "cmd.exe", "/c", "start", "", folderPath + "\\" + "AutomationReports.bat" };
		try {
			Process process = new ProcessBuilder(command).start();
		} catch (IOException e) {
			log.error("Issue with text execution" + e.getMessage());
			e.printStackTrace();
		}
		log.info("testExecution completed");
	}

	@GetMapping("/openReport")
	public void openTestDashboard() throws IOException {
		log.info("open-test-dashboard started");
		String testReportPath = "file:///D:/Anand/Node/SmartTest-AI/AutomationReports/dashboard.html";
		Runtime.getRuntime().exec("cmd /c start " + testReportPath);
		log.info("Test dashboard opened successfully");
	}

}
