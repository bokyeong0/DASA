package com.vertexid.utill;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.vertexid.dao.AttachDAO;
import com.vertexid.vo.AttachVo;



/**
 * @Class명: FileManager
 * @작성일: 2014. 9. 18
 * @작성자: 김진호
 * @설명: 파일관리자
 */
public class ImgAttachManager {

	private AttachVo vo ;
	private static final String SP = File.separator;	// 서버 구분자
	private static final String BASEFOLDER = "upload";		// 기본폴더명
	
	
	@Autowired
	private AttachDAO dao;
	
	
	
	
	public int updateFile(MultipartFile[] files , int file_m_seq, String em_no, String type) throws Exception{
		return files == null ? -1 : saveFiles(files,file_m_seq, em_no, type); 
	}
	
	private int saveFiles(MultipartFile[] files ,int file_m_seq, String em_no, String type) throws Exception{
		List<AttachVo> voList = new ArrayList<AttachVo>();
		for (MultipartFile file :files) {
			if(file.getBytes().length > 0){
				vo = new AttachVo();
				vo.setRegist_man(em_no);
				vo.setAi_size(file.getSize());
				vo.setAi_nm( file.getOriginalFilename());
				String path = uploadServer(file,"files");
				vo.setAi_path(path);
				if(!path.equals("")){
					voList.add(vo);
				}
			}else{
				System.out.println("파일은 있지만 용량이 없음 byte : "+file.getSize());
			}
		}
		return dao.attachInsertMulti(voList, em_no, file_m_seq, type);
	}
	
	
	private String uploadServer(MultipartFile file, String folder){
		
		String date = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		String dirY  = new SimpleDateFormat("yyyy").format(new Date()); 
		String dirM  = new SimpleDateFormat("MM").format(new Date()); 
		String dirD  = new SimpleDateFormat("dd").format(new Date()); 
		

		String oFileName = file.getOriginalFilename();
		//System.out.println("oFileName : " +oFileName);
		String sFileName = date +(oFileName.substring(oFileName.lastIndexOf(".")));
		//String root = request.getSession().getServletContext().getRealPath("/");
		String root = "/";
		String filePath = "";
		filePath = root +BASEFOLDER+SP+folder +SP+ dirY+SP+ dirM+SP+ dirD ;
		String dbPath = "/"+BASEFOLDER+"/"+folder+"/"+dirY+"/"+dirM+"/"+dirD;
		if(folder.equals("temp")){
			filePath = root +BASEFOLDER+SP+folder;
		}

		FileOutputStream fos = null;
		String fullPath = "";
		String dbfullPath = "";
		
		try {
			sFileName = checkFile(filePath,sFileName);
			fullPath = filePath + SP + sFileName;
			dbfullPath = dbPath +"/"+ sFileName;
			checkDir(filePath);
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(fullPath);
			fos.write(fileData);

		} catch (Exception e) {
			System.out.println("파일 저장 실패 : "+fullPath);
			dbfullPath ="";
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return dbfullPath;
	}

	private void checkDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	private String checkFile(String path, String fileNm){
		String tempFileName =  fileNm.substring(0,fileNm.lastIndexOf("."));
		String tempFileEx =  fileNm.substring(fileNm.lastIndexOf("."));
		String tempFullName =  tempFileName+tempFileEx;
		int cnt = 1;
		while (true) {
			File f = new File(path+SP+tempFullName);
			if (f.isFile()) {
				tempFullName = tempFileName+"("+cnt+")"+tempFileEx;
				cnt++;
			}else {
				break;
			}
	    }
	    return tempFullName;
	}
}
