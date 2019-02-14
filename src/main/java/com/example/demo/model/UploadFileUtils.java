package com.example.demo.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	public static ScheduleUploadFile fileSave(String uploadPath, MultipartFile file, int bno)
			throws IllegalStateException, IOException {

		File uploadPathDir = new File(uploadPath);
		System.out.println("uploadPathDir : " + uploadPathDir);
		System.out.println("uploadPathDir exists:" + uploadPathDir.exists());
		if (!uploadPathDir.exists()) {
			uploadPathDir.mkdir();
		}
		String originalfileName = file.getOriginalFilename();
		String savePath = calcPath(uploadPath);
		String finalPath = uploadPath + "/" + savePath;
		System.out.println("final dirName:" + finalPath);

		String saveFileName = timeName(originalfileName);
		File target = new File(finalPath);
		if (!target.exists()) {
			target.mkdir();
		}
		file.transferTo(new File(finalPath + "\\" + saveFileName));

		ScheduleUploadFile saveFile = new ScheduleUploadFile();
		saveFile.setBno(bno);
		saveFile.setFname(saveFileName);
		saveFile.setOfname(file.getOriginalFilename());
		saveFile.setSize(file.getSize());
		saveFile.setPath(finalPath);

		return saveFile;
	}

	public static FoodUploadFile foodfileSave(String uploadPath, MultipartFile file, int bno)
			throws IllegalStateException, IOException {

		File uploadPathDir = new File(uploadPath);
		System.out.println("uploadPathDir : " + uploadPathDir);
		System.out.println("uploadPathDir exists:" + uploadPathDir.exists());
		if (!uploadPathDir.exists()) {
			uploadPathDir.mkdir();
		}
		String originalfileName = file.getOriginalFilename();
		String savePath = calcPath(uploadPath);
		String finalPath = uploadPath + "/" + savePath;
		System.out.println("final dirName:" + finalPath);

		String saveFileName = timeName(originalfileName);
		File target = new File(finalPath);
		if (!target.exists()) {
			target.mkdir();
		}
		file.transferTo(new File(finalPath + "\\" + saveFileName));

		FoodUploadFile saveFile = new FoodUploadFile();
		saveFile.setBno(bno);
		saveFile.setFname(saveFileName);
		saveFile.setOfname(file.getOriginalFilename());
		saveFile.setSize(file.getSize());
		saveFile.setPath(finalPath);

		return saveFile;
	}
	public static NewsUploadFile newsfileSave(String uploadPath, MultipartFile file, int bno)
			throws IllegalStateException, IOException {

		File uploadPathDir = new File(uploadPath);
		System.out.println("uploadPathDir : " + uploadPathDir);
		System.out.println("uploadPathDir exists:" + uploadPathDir.exists());
		if (!uploadPathDir.exists()) {
			uploadPathDir.mkdir();
		}
		String originalfileName = file.getOriginalFilename();
		String savePath = calcPath(uploadPath);
		String finalPath = uploadPath + "/" + savePath;
		System.out.println("final dirName:" + finalPath);

		String saveFileName = timeName(originalfileName);
		File target = new File(finalPath);
		if (!target.exists()) {
			target.mkdir();
		}
		file.transferTo(new File(finalPath + "\\" + saveFileName));

		NewsUploadFile saveFile = new NewsUploadFile();
		saveFile.setBno(bno);
		saveFile.setFname(saveFileName);
		saveFile.setOfname(file.getOriginalFilename());
		saveFile.setSize(file.getSize());
		saveFile.setPath(finalPath);

		return saveFile;
	}
	public static NoticeUploadFile noticefileSave(String uploadPath, MultipartFile file, int bno)
			throws IllegalStateException, IOException {

		File uploadPathDir = new File(uploadPath);
		System.out.println("uploadPathDir : " + uploadPathDir);
		System.out.println("uploadPathDir exists:" + uploadPathDir.exists());
		if (!uploadPathDir.exists()) {
			uploadPathDir.mkdir();
		}
		String originalfileName = file.getOriginalFilename();
		String savePath = calcPath(uploadPath);
		String finalPath = uploadPath + "/" + savePath;
		System.out.println("final dirName:" + finalPath);

		String saveFileName = timeName(originalfileName);
		File target = new File(finalPath);
		if (!target.exists()) {
			target.mkdir();
		}
		file.transferTo(new File(finalPath + "\\" + saveFileName));

		NoticeUploadFile saveFile = new NoticeUploadFile();
		saveFile.setBno(bno);
		saveFile.setFname(saveFileName);
		saveFile.setOfname(file.getOriginalFilename());
		saveFile.setSize(file.getSize());
		saveFile.setPath(finalPath);

		return saveFile;
	}
	public static PhotoUploadFile photofileSave(String uploadPath, MultipartFile file, int bno)
			throws IllegalStateException, IOException {

		File uploadPathDir = new File(uploadPath);
		System.out.println("uploadPathDir : " + uploadPathDir);
		System.out.println("uploadPathDir exists:" + uploadPathDir.exists());
		if (!uploadPathDir.exists()) {
			uploadPathDir.mkdir();
		}
		String originalfileName = file.getOriginalFilename();
		String savePath = calcPath(uploadPath);
		String finalPath = uploadPath + "/" + savePath;
		System.out.println("final dirName:" + finalPath);

		String saveFileName = timeName(originalfileName);
		File target = new File(finalPath);
		if (!target.exists()) {
			target.mkdir();
		}
		file.transferTo(new File(finalPath + "\\" + saveFileName));

		PhotoUploadFile saveFile = new PhotoUploadFile();
		saveFile.setBno(bno);
		saveFile.setFname(saveFileName);
		saveFile.setOfname(file.getOriginalFilename());
		saveFile.setSize(file.getSize());
		saveFile.setPath(finalPath);

		return saveFile;
	}

	public static String calcPath(String uploadPath) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String calPath = dateFormat.format(date);
		logger.info(calPath);
		return calPath;
	}

	private static String timeName(String originalFileName) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		Date time = new Date();
		String saveName = timeFormat.format(time) + originalFileName;
		System.out.println("saveName:" + saveName);
		return saveName;
	}
}