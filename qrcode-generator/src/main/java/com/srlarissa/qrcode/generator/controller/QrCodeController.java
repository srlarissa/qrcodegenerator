package com.srlarissa.qrcode.generator.controller;
import com.srlarissa.qrcode.generator.dto.QrCodeGenerateRequest;
import com.srlarissa.qrcode.generator.dto.QrCodeGenerateResponse;
import com.srlarissa.qrcode.generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")

public class QrCodeController {
    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeService) {
        this.qrCodeGeneratorService = qrCodeService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generateQrCode(@RequestBody QrCodeGenerateRequest request){
        try{
            QrCodeGenerateResponse response = this.qrCodeGeneratorService.generateQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new QrCodeGenerateResponse("Error generating QR code: " + e.getMessage()));
        }
    }
}
