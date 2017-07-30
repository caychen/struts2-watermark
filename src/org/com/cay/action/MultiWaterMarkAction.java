package org.com.cay.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.com.cay.service.WaterMarkService;
import org.com.cay.service.impl.MultiImageMarkService;
import org.com.cay.service.impl.UploadService;
import org.com.cay.watermark.PicInfo;

import com.opensymphony.xwork2.ActionSupport;

public class MultiWaterMarkAction extends ActionSupport {

	//由struts框架组件传递过来下面两个参数
	private File[] image;
	private String[] imageFileName;

	private String uploadPath;//struts.xml中配置
	private List<PicInfo> picInfos;

	public List<PicInfo> getPicInfos() {
		return picInfos;
	}

	public void setPicInfo(List<PicInfo> picInfos) {
		this.picInfos = picInfos;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String watermark() throws Exception {
		// 上传的真实路径
		String realUploadPath = ServletActionContext.getServletContext()
				.getRealPath(uploadPath);

		// 图片上传Service
		UploadService uploadService = new UploadService();
		
		//WaterMarkService markService = new SingleWaterMarkServiceImpl();//添加单个文字水印
		//WaterMarkService markService = new SingleImageMarkService();//添加单个图片水印
		
		//WaterMarkService markService = new MultiTextMarkServiceImpl();//添加多个文字水印
		WaterMarkService markService = new MultiImageMarkService();//添加多个图片水印
		
		picInfos = new ArrayList<PicInfo>();
		
		if(image != null && image.length > 0){
			for(int i = 0;i < image.length; ++i){
				PicInfo picInfo = new PicInfo();
				
				//返回上传图片的相对路径
				picInfo.setImageURL(uploadService.uploadImage(image[i], imageFileName[i],
						uploadPath, realUploadPath));
				
				
				picInfo.setLogoImageURL(markService.watermark(image[i], imageFileName[i],
						uploadPath, realUploadPath));
				
				picInfos.add(picInfo);
			}
		}

		return "success";
	}
}
