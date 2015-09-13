package br.com.jsflab.view.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.jsflab.service.ImageService;

@ManagedBean(name = "imageMB")
@ViewScoped
public class ImageMB extends AbstractMB {

    private static final long serialVersionUID = -3215385844256696577L;

    @ManagedProperty(value = "#{imageService}")
    private ImageService imageService;

    private Part imagem;

    @Override
    public void inicializar() {

    }

    public void uploadImage() throws IOException {
        if (imagem.getSize() > 0) {
            String fileName = getFileNameFromPart(imagem);
            File outputFile = new File(getRealPath() + File.separator + "WEB-INF" + File.separator + fileName);
            InputStream inputStream = imagem.getInputStream();
            OutputStream outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        }
    }

    public void handleFileUpload(FileUploadEvent fileUploadEvent) {

        UploadedFile file = fileUploadEvent.getFile();
        System.out.println(file.getClass().getName());
    }

    public ImageService getImageService() {

        return imageService;
    }

    public void setImageService(ImageService imageService) {

        this.imageService = imageService;
    }

    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }
}