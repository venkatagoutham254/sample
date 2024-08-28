package com.aforo.dataingestion;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CSVController {
    @Autowired
    private CSVService csvService;

    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCSV(@RequestParam String filePath) {
        try {
            csvService.readAndStoreCSVData(filePath);
            return ResponseEntity.ok("CSV data processed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing CSV file");
        }
    }
}
