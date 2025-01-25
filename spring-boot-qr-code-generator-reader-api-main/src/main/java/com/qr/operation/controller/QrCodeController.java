package com.qr.operation.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.qr.operation.dto.QrCodeGenerationRequestDto;
import com.qr.operation.service.QrCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/QRData")
public class QrCodeController {

	private final QrCodeService qrCodeService;

	@PostMapping(value = "/generate")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns a .png QR code with provided information decoded inside")
	public void qrCodeGenerationHandler(
			@Valid @RequestBody(required = true) final QrCodeGenerationRequestDto qrCodeGenerationRequestDto,
			final HttpServletResponse httpServletResponse) throws IOException, WriterException {
		qrCodeService.generate(qrCodeGenerationRequestDto, httpServletResponse);
	}

	@PutMapping(value = "/read", consumes = "multipart/form-data")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "returns decoded information inside provided QR code")
	public ResponseEntity<?> read(
			@Parameter(description = ".png image of QR code generated through this portal") @RequestParam(value = "file", required = true) MultipartFile file)
			throws IOException, NotFoundException {
		return qrCodeService.read(file);
	}

}
