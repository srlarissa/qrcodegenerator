package com.srlarissa.qrcode.generator.infrastructure.ports;

public interface StoragePort {
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
