package com.example.demo.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FoodUploadFile;
import com.example.demo.model.NewsUploadFile;
import com.example.demo.model.NoticeUploadFile;
import com.example.demo.model.PhotoUploadFile;
import com.example.demo.model.ScheduleUploadFile;
import com.example.demo.model.UploadFileUtils;
import com.example.demo.repository.FoodFileRepository;
import com.example.demo.repository.NewsFileRepository;
import com.example.demo.repository.NoticeFileRepository;
import com.example.demo.repository.PhotoFileRepository;
import com.example.demo.repository.ScheduleFileRepository;


@Service
public class ImageService {
    
//	static String path = "/home/juya";
	static String path = "C:/woo/주야업로드";

    @Autowired
    ScheduleFileRepository scheRepo;
    @Autowired
    FoodFileRepository foodRepo;
    @Autowired
    NewsFileRepository newsRepo;
    @Autowired
    NoticeFileRepository noticeRepo;
    @Autowired
    PhotoFileRepository photoRepo;
    
    

    public ScheduleUploadFile store(MultipartFile file, String subPath, int bno) throws Exception {
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            
            ScheduleUploadFile saveFile = UploadFileUtils.fileSave(path+"/"+subPath, file, bno);
            scheRepo.save(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    
    public FoodUploadFile foodstore(MultipartFile file, String subPath, int bno) throws Exception  {
    	try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            
            FoodUploadFile saveFile = UploadFileUtils.foodfileSave(path+"/"+subPath, file, bno);
            foodRepo.save(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    public NewsUploadFile newsStore(MultipartFile file, String subPath, int bno) throws Exception  {
    	try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            
            NewsUploadFile saveFile = UploadFileUtils.newsfileSave(path+"/"+subPath, file, bno);
            newsRepo.save(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    public NoticeUploadFile noticeStore(MultipartFile file, String subPath, int bno) throws Exception  {
    	try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            
            NoticeUploadFile saveFile = UploadFileUtils.noticefileSave(path+"/"+subPath, file, bno);
            noticeRepo.save(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    public PhotoUploadFile photoStore(MultipartFile file, String subPath, int bno) throws Exception  {
    	try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            
            PhotoUploadFile saveFile = UploadFileUtils.photofileSave(path+"/"+subPath, file, bno);
            photoRepo.save(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    
    public void down(HttpServletRequest req, HttpServletResponse res, String ofname, String fname, String subpath) {
    	if(subpath.equals("schedule")) {
    		ScheduleUploadFile list = (ScheduleUploadFile) scheRepo.findScheduleUploadFileByFname(fname);
        	String path = list.getPath();
        	String listfname = list.getFname();
    		File file = new File(path+"/"+listfname);
    		System.out.println("file.exists:"+file.exists());
    		down(req, res, ofname, file);	
    	}
    	if(subpath.equals("food")) {
    		FoodUploadFile list = foodRepo.findFoodUploadFileByFname(fname);
    		String path = list.getPath();
        	String listfname = list.getFname();
        	File file = new File(path+"/"+listfname);
    		System.out.println("file.exists:"+file.exists());
    		down(req, res, ofname, file);
    	}
    	if(subpath.equals("news")) {
    		NewsUploadFile list = newsRepo.findNewsUploadFileByFname(fname);
    		String path = list.getPath();
        	String listfname = list.getFname();
        	File file = new File(path+"/"+listfname);
    		System.out.println("file.exists:"+file.exists());
    		down(req, res, ofname, file);
    	}
    	if(subpath.equals("notice")) {
    		NoticeUploadFile list = noticeRepo.findNoticeUploadFileByFname(fname);
    		String path = list.getPath();
        	String listfname = list.getFname();
        	File file = new File(path+"/"+listfname);
    		System.out.println("file.exists:"+file.exists());
    		down(req, res, ofname, file);
    	}
    	if(subpath.equals("photo")) {
    		PhotoUploadFile list = photoRepo.findPhotoUploadFileByFname(fname);
    		String path = list.getPath();
        	String listfname = list.getFname();
        	File file = new File(path+"/"+listfname);
    		System.out.println("file.exists:"+file.exists());
    		down(req, res, ofname, file);
    	}
	}
	
	public static void down(HttpServletRequest req, HttpServletResponse res, String originalFileName, File file) {
		res.setContentType("application/octet-stream");
		String Agent = req.getHeader("USER-AGENT");

		try {
			if (Agent.indexOf("MSIE") >= 0) {
				int i = Agent.indexOf('M', 2);
				String IEV = Agent.substring(i + 5, i + 8);
				if (IEV.equalsIgnoreCase("5.5")) {
					res.setHeader("Content-Disposition",
							"filename=" + new String(originalFileName.getBytes("euc-kr"), "8859_1"));
				} else {
					res.setHeader("Content-Disposition",
							"attachment;filename=" + new String(originalFileName.getBytes("euc-kr"), "8859_1"));
				}
			} else {
				res.setHeader("Content-Disposition",
						"attachment;filename=" + new String(originalFileName.getBytes("euc-kr"), "8859_1"));
			}
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}

		byte b[] = new byte[1024];

		if (file.isFile()) {
			try {
				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream outs = new BufferedOutputStream(res.getOutputStream());

				int read = 0;

				while ((read = fin.read(b)) != -1) {
					outs.write(b, 0, read);
				}

				outs.flush();
				outs.close();
				fin.close();
			} catch (Exception e) {
			}
		}
	}
}