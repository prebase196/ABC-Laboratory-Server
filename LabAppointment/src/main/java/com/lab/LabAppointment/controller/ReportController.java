package com.lab.LabAppointment.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lab.LabAppointment.model.Report;
import com.lab.LabAppointment.model.User;
import com.lab.LabAppointment.repository.ReportRepo;

@RestController
@RequestMapping("/api/v1/reports")
@CrossOrigin(origins = "*")
public class ReportController {

	@Autowired
    private final ReportRepo reportRepo;

    public ReportController(ReportRepo reportRepo) {
        this.reportRepo = reportRepo;
    }

    @GetMapping
    public List<Report> getAllReport() {
        return reportRepo.findAll();
    }

    @PostMapping
//    @RequestMapping(value = "/file")
    public ResponseEntity<Report> createReport(@RequestParam("file") MultipartFile file, @RequestParam("userId") User userId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            byte[] bytes = file.getBytes();
            String filename = file.getOriginalFilename();
            String uploadDir = "D:\\2024\\Month\\March\\StoredReport"; // Specify the directory where you want to store the files
            Path path = Paths.get(uploadDir + filename);
            Files.write(path, bytes);

            Report reportNew = new Report();
            reportNew.setUser(userId);
            reportNew.setReportDate(new Date());
            reportNew.setReportFilePath(path.toString());

            Report savedReport = reportRepo.save(reportNew);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file, @RequestParam("userId") User userId) {

        String fileName = file.getOriginalFilename();
        String uploadDir = "D:/StoredReport/";

        try {
            // Ensure the target directory exists, create it if not
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Save the file to the specified location
            file.transferTo(new File(uploadDir + fileName));
            System.out.println("File saved successfully.");
            String path = uploadDir+fileName;

            Report reportNew = new Report();
            reportNew.setUser(userId);
            reportNew.setReportDate(new Date());
            reportNew.setReportFilePath(path.toString());

            Report savedReport = reportRepo.save(reportNew);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
