package org.com.cay.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadService {

	public String uploadImage(File image, String imageFileName,
			String uploadPath, String realUploadPath) {

		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(image);
			
			os = new FileOutputStream(realUploadPath + File.separator
					+ imageFileName);

			byte[] buffer = new byte[1024];
			int len = 0;

			while ((len = is.read(buffer)) > 0) {
				os.write(buffer);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return uploadPath + "/" + imageFileName;
	}
}
