package br.com.jsflab.service;

import java.io.File;
import java.util.List;

public interface ImageService {

	public void upload(File file);

	public List<File> findAll();

	public List<File> download(Long... ids);
}
