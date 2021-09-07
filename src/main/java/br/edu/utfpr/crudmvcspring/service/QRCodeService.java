package br.edu.utfpr.crudmvcspring.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class QRCodeService {

    /**
     * Gera um QR Code e persiste em arquivo no formato PNG
     * @param data dado a ser persistido no QR Code
     * @param height
     * @param width
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public File generateQRFilePNG(String data, int height, int width) throws WriterException, IOException {
        String charset = "UTF-8";
        Path path = Files.createTempFile("qr", ".png");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String dataCharset = new String(data.getBytes(charset), charset);
        BitMatrix bitMatrix = qrCodeWriter.encode(dataCharset, BarcodeFormat.QR_CODE, width, height);

        //Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return path.toFile();
    }

    public File generateQRFileSVG(String data, int height, int width) throws WriterException, IOException {
        String charset = "UTF-8";
        Path path = Files.createTempFile("qr", ".svg");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String dataCharset = new String(data.getBytes(charset), charset);
        BitMatrix bitMatrix = qrCodeWriter.encode(dataCharset, BarcodeFormat.QR_CODE, width, height);

        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        SVGDocument document = (SVGDocument) domImpl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);

        SVGGraphics2D g2 = new org.apache.batik.svggen.SVGGraphics2D(document);
        g2.setColor(Color.BLACK);

        for (int xIndex = 0; xIndex < bitMatrix.getWidth(); xIndex = xIndex + bitMatrix.getRowSize()) {
            for (int yIndex = 0; yIndex < bitMatrix.getWidth(); yIndex = yIndex + bitMatrix.getRowSize()) {
                if (bitMatrix.get(xIndex, yIndex)) {
                    g2.fillRect(xIndex, yIndex, bitMatrix.getRowSize(), bitMatrix.getRowSize());
                }
            }
        }

        Writer out = new OutputStreamWriter(new FileOutputStream(path.toFile()));
        g2.stream(out, true);
        return path.toFile();
    }

    /**
     * Gerar um QR Code com tamanho default
     * @param data
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public File generateQRFilePNG(String data) throws WriterException, IOException {
        return generateQRFilePNG(data, 500, 500);

    }

    /**
     * Gera o QR Code e retorna a imagem como um array de bytes.
     * @param data
     * @param height
     * @param width
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public byte[] generateQRStream(String data, int height, int width) throws WriterException, IOException{
        String charset = "UTF-8";

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String dataCharset = new String(data.getBytes(charset), charset);
        BitMatrix bitMatrix = qrCodeWriter.encode(dataCharset, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] stream = pngOutputStream.toByteArray();

        return stream;
    }

    public byte[] generateQRStream(String data) throws WriterException, IOException{
        return generateQRStream(data, 500, 500);
    }

    /**
     * Gera o QR Code e retorna em Base64
     * @param data
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public String generateQRBase64(String data) throws IOException, WriterException {
        byte[] qrCodeStream = generateQRStream(data);
        String qrCodeBase64 = Base64Utils.encodeToString(qrCodeStream);
        return "data:image/png;base64," + qrCodeBase64;
    }
}
