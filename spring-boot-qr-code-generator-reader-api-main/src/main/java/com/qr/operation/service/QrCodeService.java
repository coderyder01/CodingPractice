package com.qr.operation.service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qr.operation.dto.QrCodeGenerationRequestDto;

import static com.qr.operation.utils.Constants.SLASH;

@Service
public class QrCodeService {

	public ResponseEntity<?> read(final MultipartFile file) throws IOException, NotFoundException {
		BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		Result result = new MultiFormatReader().decode(binaryBitmap);
		return ResponseEntity.ok(new ObjectMapper().readValue(result.getText(), QrCodeGenerationRequestDto.class));
	}

	public void generate(final QrCodeGenerationRequestDto qrCodeGenerationRequestDto,
			final HttpServletResponse httpServletResponse) throws IOException, WriterException {
		httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				"attachment;filename=" + qrCodeGenerationRequestDto.getTitle().trim().replace(" ", "_") + ".png");

		final var outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
		String url = "https://192.168.1.10:9001/api/QRData"+SLASH+qrCodeGenerationRequestDto.getGeneratedForName(); // The URL you want the QR code to encode
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300);
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
		outputStream.flush();
	}

}
