package br.com.jsflab.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

	@Override
	public void upload(File file) {

		// TODO Auto-generated method stub

	}

	@Override
	public List<File> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> download(Long... ids) {

		// TODO Auto-generated method stub
		return null;
	}

}
