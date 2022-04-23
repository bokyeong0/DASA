package com.vertexid.utill;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.activity.vo.ActivityFixingTrtVo;
import com.dasa.activity.vo.ActivityRndOddVo;
import com.dasa.activity.vo.ActivityRndTrtVo;
import com.vertexid.vo.ExcelVo;

public class ExcelManager {
	
	
	private static final String SP = File.separator;	// 서버 구분자
	private static final String BASEFOLDER = "upload";		// 기본폴더명
	
	@Autowired(required = true)
	private HttpServletRequest request;
	
	@Autowired(required = true)
	private HttpSession session;
	
	private XSSFWorkbook workbook;	// 1차로 workbook을 생성
	private XSSFSheet sheet;		// 2차는 sheet생성
	private XSSFRow row; 			// 엑셀의 행
	private XSSFCell cell;			// 엑셀의 셀
	private String oFileName; 
	private String fileExt  =".xlsx";
	private String sheetName;
	private String[] sheetNames;
	private String[] matters;
	private String folder = "excel";
	private String sFileName ;  
	private String fullPath;
	private String codeAt;          //A20171226 k2s 코드포함여부
	
	private List<ActivityFixingTrtVo> fixingTrtList;
	private List<ActivityRndTrtVo> trtList;	 
	private List<ActivityRndOddVo> oddList;
	private List<Map<String, String>> firstColumnList ; 
	private List<Map<String, String>> secondColumnList ; 
	private List<Map<String, String>> sumColumnList ; 
	private List<Map<String, String>> bodyList ;
	private List<List<Map<String, String>>> columnLists ; 
	private List<List<Map<String, String>>> bodyLists ;
	
	public ExcelManager(){
		super(); 
	}
	
	public void makeListExcel(ExcelVo vo){
		this.firstColumnList  = vo.getColumnList();
		this.secondColumnList = vo.getColumnSecondList();
		this.sumColumnList    = vo.getSumList();
		this.bodyList         = vo.getBodyList();
		this.oFileName        = vo.getFileName();
		this.sheetName        = vo.getSheetName();
		this.codeAt           = vo.getCodeAt();
		
		workbook = new XSSFWorkbook(); 		        // 1차로 workbook을 생성
		sheet    = workbook.createSheet(sheetName); // 2차는 sheet생성
		
		int firstColLen    = firstColumnList.size(); 
		int sumColLen      = sumColumnList.size();
		int secondColLen   = 0;
		int intHeaderIndex = 6;    //A20171220 k2s header index 변경 수치를 변경 조치하기 위함
		
		if ("Y".equals(codeAt)) {  //A20171226 k2s 코드포함여부
			intHeaderIndex = 11;
		}
		
		if(secondColumnList != null){
			secondColLen = secondColumnList.size(); 
		}
		
		int bodyLen = bodyList.size();
		
		//바디 굵은선 금액 스타일
		XSSFCellStyle numberStyle = workbook.createCellStyle();
		XSSFDataFormat df         = workbook.createDataFormat();
		numberStyle.setDataFormat(df.getFormat("#,##0.0"));
		
		XSSFCellStyle columnStyle  = workbook.createCellStyle();
		columnStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		columnStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		columnStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		columnStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		columnStyle.setBorderTop(CellStyle.BORDER_THIN);
		columnStyle.setBorderLeft(CellStyle.BORDER_THIN);
		columnStyle.setBorderRight(CellStyle.BORDER_THIN);
		columnStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		XSSFCellStyle columnStyle2 = workbook.createCellStyle();
		columnStyle2.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		columnStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		columnStyle2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
				
		if ((firstColumnList != null && firstColLen > 0) && (bodyList != null && bodyLen > 0)) {
			
			int rowIndex = 0;

			// 컬럼 생성
			row = sheet.createRow((short) rowIndex);
			String defaultCol[]      = new String[intHeaderIndex];
			short  defaultColWidth[] = new short[intHeaderIndex];
			
			if (intHeaderIndex == 6) {                 //A20171226 k2s 코드비포함 
				defaultCol[0]  = "고객그룹";
				defaultCol[1]  = "관리업체";
				defaultCol[2]  = "매장명";
				defaultCol[3]  = "매장코드";
				defaultCol[4]  = "사원명";
				defaultCol[5]  = "차수";
				
				defaultColWidth[0]  = 3411;
				defaultColWidth[1]  = 4411;
				defaultColWidth[2]  = 5411;
				defaultColWidth[3]  = 2411;
				defaultColWidth[4]  = 2411;
				defaultColWidth[5]  = 2411;
			}
			
			if (intHeaderIndex == 11) {                //A20171226 k2s 코드포함(지점명, 고객그룹코드, 관리업체코드, 권역1, 권역2)
				defaultCol[0]  = "지점";
				defaultCol[1]  = "고객그룹";
				defaultCol[2]  = "고객그룹코드";
				defaultCol[3]  = "관리업체";
				defaultCol[4]  = "관리업체코드";
				defaultCol[5]  = "매장명";
				defaultCol[6]  = "매장코드";
				defaultCol[7]  = "사원명";
				defaultCol[8]  = "차수";
				defaultCol[9]  = "권역1";
				defaultCol[10] = "권역2";
				
				defaultColWidth[0]  = 2411;
				defaultColWidth[1]  = 2411;
				defaultColWidth[2]  = 3411;
				defaultColWidth[3]  = 5411;
				defaultColWidth[4]  = 3411;
				defaultColWidth[5]  = 5411;
				defaultColWidth[6]  = 2411;
				defaultColWidth[7]  = 2411;
				defaultColWidth[8]  = 1411;
				defaultColWidth[9]  = 2411;
				defaultColWidth[10] = 3411;				
			}
			
			int deLen = defaultCol.length;
			
			
			if(secondColLen > 0 ){
				for (int i = 0; i < defaultCol.length; i++) {
 
				}
			}
			
			//고객그룹, 관리업체, 매장명, 매장코드, 사원명, 차수.. 헤더 생성
			for (int j = 0; j < deLen; j++) {
				cell = row.createCell(j);
				cell.setCellValue(String.valueOf(defaultCol[j])); 
				sheet.setColumnWidth(j, (short)defaultColWidth[j]);
				cell.setCellStyle(columnStyle);
			}
			
			//품목 헤더 생성
			for (int i = 0; i < firstColLen; i++) {
				String columnName = firstColumnList.get(i).get("group_nm");
				cell = row.createCell(i+deLen);
				cell.setCellValue(String.valueOf(columnName)); 
				cell.setCellStyle(columnStyle);
			}
			
			rowIndex++;  //행증가
			
			row = sheet.createRow((short) rowIndex);

			//고객그룹, 관리업체, 매장명, 매장코드, 사원명, 차수.. 헤더 생성			
			for (int j = 0; j < deLen; j++) {
				cell = row.createCell(j);
				cell.setCellValue(String.valueOf(defaultCol[j])); 
				sheet.setColumnWidth(j, (short)defaultColWidth[j]);
				cell.setCellStyle(columnStyle);
			}
			
			//진열현황 비교업체 헤더 생성 
			for (int i = 0; i < secondColLen; i++) { 
				String columnName = secondColumnList.get(i).get("cellName");
				cell = row.createCell(i+deLen);
				cell.setCellValue(String.valueOf(columnName)); 
				cell.setCellStyle(columnStyle);
			}
			
			//고객그룹, 관리업체, 매장명, 매장코드, 사원명, 차수 헤더 각각 (1+2) 셀 병합	
			if(secondColLen > 0 ){
				rowIndex++;
				for (int i = 0; i < defaultCol.length; i++) {
					sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));  //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)
				}
			}
			
			ArrayList<String> keyList       = new ArrayList<String>();
			ArrayList<String> keyList2      = new ArrayList<String>();
			ArrayList<String> keyHeaderList = new ArrayList<String>();
			
			if (intHeaderIndex == 6) {        //A20171226 k2s           
				keyList.add("cg_nm");         //고객그룹
				keyList.add("me_nm");         //관리업체
				keyList.add("sm_nm");         //매장
				keyList.add("sm_sap_code");   //SAP매장코드
				keyList.add("em_nm");         //MD명
				keyList.add("sm_odr");        //SPC차수
				
				keyHeaderList.add("cg_nm");
				keyHeaderList.add("me_nm");
				keyHeaderList.add("sm_nm");
				keyHeaderList.add("sm_sap_code");
				keyHeaderList.add("em_nm");
				keyHeaderList.add("sm_odr");
			}
			
			if (intHeaderIndex == 11) {       //A20171226 k2s 코드포함(지점명, 고객그룹코드, 관리업체코드, 권역1, 권역2) 
				keyList.add("om_nm");         //지점명
				keyList.add("cg_nm");         //고객그룹
				keyList.add("cg_code");       //고객그룹코드
				keyList.add("me_nm");         //관리업체
				keyList.add("me_code");       //관리업체코드
				keyList.add("sm_nm");         //매장
				keyList.add("sm_sap_code");   //SAP매장코드
				keyList.add("em_nm");         //MD명
				keyList.add("sm_odr");        //SPC차수
				keyList.add("sm_area1_nm");   //권역명1(시,도)
				keyList.add("sm_area2_nm");   //권역명2(시,군,구)
				
				keyHeaderList.add("om_nm");
				keyHeaderList.add("cg_nm");
				keyHeaderList.add("cg_code");
				keyHeaderList.add("me_nm");
				keyHeaderList.add("me_code");
				keyHeaderList.add("sm_nm");
				keyHeaderList.add("sm_sap_code");
				keyHeaderList.add("em_nm");
				keyHeaderList.add("sm_odr");
				keyHeaderList.add("sm_area1_nm");
				keyHeaderList.add("sm_area2_nm");
			}
			
			for (Map<String, String> map : firstColumnList) {
				keyList.add(map.get("cellId"));                           //ent_+groupId+_+itemId
				keyHeaderList.add(map.get("group_nm"));                   //품목
			}
			
			for (Map<String, String> map2 : sumColumnList) {
				keyList2.add(map2.get("cellId"));                         //rate_+tempGroupId+_DSF
			}
			
			boolean margeFlag = true;
			int    margeIndex = intHeaderIndex;                     //M20171220 k2s 6-> 8  8-> 11 11-> intHeaderIndex
			String    tempKey = keyHeaderList.get(intHeaderIndex);  //M20171220 k2s 6-> 8  8-> 11 11-> intHeaderIndex
			
			
			int flag = 0;
			for (Map<String, String> bodyMap : bodyList) { 
				int DSFIndex = intHeaderIndex;                      //M20171220 k2s 6-> 8  8-> 11 11-> intHeaderIndex
				row          = sheet.createRow((short) rowIndex);
				
				if (firstColumnList != null && firstColumnList.size() > 0) {
					
					for(int j=0; j<keyList.size(); j++) {
						String key = keyList.get(j);
						if(key.indexOf("DSF") > 0 ){
							flag = flag + 1;
						}else{
							flag = flag + 0;
						}
					}
					
					for (int i = 0; i < keyList.size(); i++) { 
						cell             = row.createCell(i);
						String key       = keyList.get(i);
						String headerKey = keyHeaderList.get(i);
						int rIdx         = rowIndex+1;
						
						for(int j=0; j<keyList.size(); j++) {
							if(key.indexOf("DSF") > 0 ){
								flag = flag + 1;
							}else{
								continue;
							}
						}
						
						if(key.indexOf("DSF") > 0 ){
							cell.setCellStyle(numberStyle); 
							//엑셀산식 설정
							String formula = "IF("+getColumnId(DSFIndex)+""+rIdx+" =0, 0 ,("+getColumnId(DSFIndex)+""+rIdx+"*100)/SUM("+getColumnId(DSFIndex)+""+rIdx+":"+getColumnId(i-1)+""+rIdx+"))";
							cell.setCellFormula(formula);
							DSFIndex = i+1;
							
						}else{
							if(i < intHeaderIndex){   //M20171220 k2s 6-> 8  8-> 11 11-> intHeaderIndex
								if("sm_odr".equals(key)) {
									String strVal = String.valueOf(StringCheck.nullToRplace(bodyMap.get(key),""));
									if(!"".equals(strVal)) {
										cell.setCellValue(NumberCheck.nullToZero(strVal));  //int convert
									}

								} else {
									cell.setCellValue(String.valueOf(StringCheck.nullToRplace(bodyMap.get(key),"")));
									
								}
								
							}else{
								if(!tempKey.equals(headerKey) &&  i > intHeaderIndex && margeFlag){   //M20171220 k2s 6-> 8  8-> 11 11-> intHeaderIndex
									tempKey = headerKey;
									sheet.addMergedRegion(new CellRangeAddress(0, 0,margeIndex, i-1)); //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)
									margeIndex = i;							
								}
							 
								if(flag > 0) {
									cell.setCellValue(NumberCheck.nullToZero2(bodyMap.get(key)));
								}else {
									/*M20170406 kks 문자형 데이터를 수치 데이터로 반영*/
									String strVal= StringCheck.nullToRplace2(bodyMap.get(key),"");  //float convert
									if(strVal == ""){
										//cell.setCellValue(null);
									}else{
//										cell.setCellValue(Integer.parseInt(value));
										/*M20170421 kks 문자형 데이터를 수치 데이터(소수점포함)로 반영 Float-> Double*/
										cell.setCellValue(NumberCheck.nullToZero3(strVal));        //double convert
										
									}
								}//ifend
							}//ifend
							
						}//ifend
					}//forend
				}//ifend
				rowIndex++;
				margeFlag = false;
			}//forend
			
			sheet.addMergedRegion(new CellRangeAddress(0, 0,margeIndex, keyHeaderList.size()-1)); //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)
			
			for (Map<String, String> sumMap : sumColumnList) {
				int DSFIndex = intHeaderIndex;   ////M20171220 k2s 6-> 8  8-> 11 11-> intHeaderIndex
				row          = sheet.createRow((short) rowIndex);
				
				if (firstColumnList != null && firstColumnList.size() > 0) {
					for (int i = 0; i < keyList.size(); i++) {
						cell       = row.createCell(i);
						String key = keyList.get(i);
						int rIdx   = rowIndex+1;
						
						
						if(key.indexOf("DSF") > 0 ){
							cell.setCellStyle(numberStyle);
							//엑셀산식 설정
							String formula = "IF("+getColumnId(DSFIndex)+""+rIdx+" =0, 0 ,("+getColumnId(DSFIndex)+""+rIdx+"*100)/SUM("+getColumnId(DSFIndex)+""+rIdx+":"+getColumnId(i-1)+""+rIdx+"))";
							cell.setCellFormula(formula);
							DSFIndex = i+1;
							
						}else {
							if(i == 0){         //M20170522 kks i < 6 -> i == 0 변경 처리함 엑셀파일 오픈시 오류발생(제거된 레코드: /xl/worksheets/sheet1.xml 부분의 셀 병합) 
								if(secondColLen == 0) {
									sheet.addMergedRegion(new CellRangeAddress(bodyLen+1, bodyLen+1, 0, intHeaderIndex-1)); //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol) M20171220 k2s 5-> 7  7-> intHeaderIndex-1
									cell.setCellValue(String.valueOf("	취급율	"));
									cell.setCellStyle(columnStyle2);
									
								}else {
									sheet.addMergedRegion(new CellRangeAddress(bodyLen+2, bodyLen+2, 0, intHeaderIndex-1)); //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol) M20171220 k2s 5-> 7  7-> intHeaderIndex-1
									cell.setCellValue(String.valueOf("	합  계	"));
									cell.setCellStyle(columnStyle2);
								}
							}
							
							else{
								if(secondColLen == 0) {
									if (sumMap.get(key) != null) {
//										System.out.println("sumMap.get(key): [" + String.valueOf(sumMap.get(key))+"]");
										float floatVal               = Float.parseFloat(String.valueOf(sumMap.get(key))) / (rowIndex - 1);
										DecimalFormat decimalFormat  = new DecimalFormat(".##");
										DecimalFormat decimalFormat2 = new DecimalFormat("###,##");
										
										String strVal                = decimalFormat.format(floatVal);
										String strVal2               = decimalFormat2.format(Float.parseFloat(strVal)*100);
										cell.setCellValue(strVal2+"%");
									}
									
								}else {
									cell.setCellValue(NumberCheck.nullToZero3(String.valueOf(sumMap.get(key))));  //double convert
									
								}//ifend
							}//ifend
						}//ifend
						
					}//forend
				}//ifend
			}//forend
			keyList.clear();         //A20180105 k2s
			keyList2.clear();        //A20180105 k2s
			keyHeaderList.clear();   //A20180105 k2s
		}//ifend
		
		/** 파일 write */
		uploadServer(workbook);
	}
	
	public FileSystemResource downloadFile(ExcelVo vo,HttpServletResponse response)throws Exception  {
		makeListExcel(vo);
	    File file = new File(fullPath);
	    
	    String userAgent = request.getHeader("User-Agent");
	    String ext = oFileName.substring(oFileName.lastIndexOf(".") + 1).toUpperCase(); // 확장자를 대문자로
	    oFileName = oFileName.substring(0, oFileName.lastIndexOf(".") + 1) + ext;
	    //System.out.println("다운로드파일명 : "+oFileName);
	    if ((userAgent.indexOf("Chrome") > -1 || userAgent.indexOf("Safari") > -1  ) && userAgent.indexOf("Android") < 0 ) { //크롬Safari
	    	oFileName =  Encoder.utfToiso(oFileName);
	    } else { // 나머지
	    	oFileName =  URLEncoder.encode(oFileName, "UTF-8").replaceAll("\\+", "%20");
	    }
		response.setHeader("Content-Type", "application/force-download");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + oFileName+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Accept-Ranges", "bytes");
	    return new FileSystemResource(file);
	}

	public FileSystemResource downloadFileRnd(ExcelVo vo, HttpServletResponse response)throws Exception {
		
		makeListExcelRnd(vo);
	    File file = new File(fullPath);
	    
	    String userAgent = request.getHeader("User-Agent");
	    String ext = oFileName.substring(oFileName.lastIndexOf(".") + 1).toUpperCase(); // 확장자를 대문자로
	    oFileName = oFileName.substring(0, oFileName.lastIndexOf(".") + 1) + ext;
	    //System.out.println("다운로드파일명 : "+oFileName);
	    if ((userAgent.indexOf("Chrome") > -1 || userAgent.indexOf("Safari") > -1  ) && userAgent.indexOf("Android") < 0 ) { //크롬Safari
	    	oFileName =  Encoder.utfToiso(oFileName);
	    } else { // 나머지
	    	oFileName =  URLEncoder.encode(oFileName, "UTF-8").replaceAll("\\+", "%20");
	    }
		response.setHeader("Content-Type", "application/force-download");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + oFileName+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Accept-Ranges", "bytes");
	    return new FileSystemResource(file);
	}
	public FileSystemResource downloadFileFixing(ExcelVo vo, HttpServletResponse response)throws Exception {
		
		makeListExcelFixing(vo);
		File file = new File(fullPath);
		
		String userAgent = request.getHeader("User-Agent");
		String ext = oFileName.substring(oFileName.lastIndexOf(".") + 1).toUpperCase(); // 확장자를 대문자로
		oFileName = oFileName.substring(0, oFileName.lastIndexOf(".") + 1) + ext;
		//System.out.println("다운로드파일명 : "+oFileName);
		if ((userAgent.indexOf("Chrome") > -1 || userAgent.indexOf("Safari") > -1  ) && userAgent.indexOf("Android") < 0 ) { //크롬Safari
			oFileName =  Encoder.utfToiso(oFileName);
		} else { // 나머지
			oFileName =  URLEncoder.encode(oFileName, "UTF-8").replaceAll("\\+", "%20");
		}
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + oFileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Accept-Ranges", "bytes");
		return new FileSystemResource(file);
	}
	
	public void makeListExcelFixing(ExcelVo vo) throws Exception{
		
		
		
		this.trtList = vo.getRndTrtList();
		this.oddList = vo.getRndOddList();
		this.columnLists = vo.getColumnLists();
		this.bodyLists = vo.getBodyLists();
		this.oFileName = vo.getFileName();
		this.sheetNames = vo.getSheetNames();
		this.matters = vo.getMatters();
		
		workbook = new XSSFWorkbook(); 				// 1차로 workbook을 생성
		
		//바디 굵은선 금액 스타일
		XSSFCellStyle numberStyle = workbook.createCellStyle();
		numberStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		numberStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		numberStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberStyle.setBorderRight(CellStyle.BORDER_THIN);
		numberStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		XSSFCellStyle centerStyle = workbook.createCellStyle();
		centerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		centerStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		centerStyle.setBorderTop(CellStyle.BORDER_THIN);
		centerStyle.setBorderLeft(CellStyle.BORDER_THIN);
		centerStyle.setBorderRight(CellStyle.BORDER_THIN);
		centerStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
//		XSSFCellStyle bodyStyle = workbook.createCellStyle();
		
		XSSFCellStyle columnStyle = workbook.createCellStyle();
		columnStyle.setFillForegroundColor( IndexedColors.GREY_25_PERCENT.getIndex());
		columnStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		columnStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		columnStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		columnStyle.setBorderTop(CellStyle.BORDER_THIN);
		columnStyle.setBorderLeft(CellStyle.BORDER_THIN);
		columnStyle.setBorderRight(CellStyle.BORDER_THIN);
		columnStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		
		for (int s = 0; s < sheetNames.length; s++) {
			sheet = workbook.createSheet(sheetNames[s]); 	// 2차는 sheet생성
			
			if(s == 0){
				
				firstColumnList = columnLists.get(s);
				bodyList = bodyLists.get(s);
				
				int firstColLen = firstColumnList.size(); 
				int bodyLen = bodyList.size();
				
				if ((firstColumnList != null && firstColLen > 0) && (bodyList != null && bodyLen > 0)) {
					
					int rowIndex = 0;
					
					// 컬럼 생성
					row = sheet.createRow((short) rowIndex);
					
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("구분")); 
					cell.setCellStyle(columnStyle);
					sheet.addMergedRegion(new CellRangeAddress(0,1,0, 0));
					for (int i = 0; i < firstColLen*2; i++) {
						
						String columnName = firstColumnList.get(i/2).get("oi_code_nm");
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(columnName)); 
						cell.setCellStyle(columnStyle);
						i++;
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(columnName)); 
						cell.setCellStyle(columnStyle);
						sheet.addMergedRegion(new CellRangeAddress(0,0,i, i+1));
					}
					int lastColCnt = (firstColLen*2);
					lastColCnt++;
					cell = row.createCell(lastColCnt);
					cell.setCellValue(String.valueOf("POG진열률")); 
					cell.setCellStyle(columnStyle);
//					sheet.addMergedRegion(new CellRangeAddress(0,1,lastColCnt, lastColCnt));
					lastColCnt++;
					cell = row.createCell(lastColCnt);
					cell.setCellValue(String.valueOf("실제진열율")); 
					cell.setCellStyle(columnStyle);
//					sheet.addMergedRegion(new CellRangeAddress(0,1,lastColCnt, lastColCnt));
					lastColCnt++;
					cell = row.createCell(lastColCnt);
					cell.setCellValue(String.valueOf("특이사항")); 
					cell.setCellStyle(columnStyle);
//					sheet.addMergedRegion(new CellRangeAddress(0,1,lastColCnt, lastColCnt));
					
					rowIndex++;
					row = sheet.createRow((short) rowIndex);
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("구분")); 
					cell.setCellStyle(columnStyle);
					for (int i = 0; i < firstColLen*2; i++) {
						
						
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf("POG")); 
						cell.setCellStyle(columnStyle);
						i++;
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf("현재")); 
						cell.setCellStyle(columnStyle);
					}
					lastColCnt = (firstColLen*2);
					lastColCnt++;
					cell = row.createCell(lastColCnt);
					cell.setCellValue(String.valueOf("POG진열률")); 
					cell.setCellStyle(columnStyle);
					sheet.addMergedRegion(new CellRangeAddress(0,1,lastColCnt, lastColCnt));
					lastColCnt++;
					cell = row.createCell(lastColCnt);
					cell.setCellValue(String.valueOf("실제진열율")); 
					cell.setCellStyle(columnStyle);
					sheet.addMergedRegion(new CellRangeAddress(0,1,lastColCnt, lastColCnt));
					lastColCnt++;
					cell = row.createCell(lastColCnt);
					cell.setCellValue(String.valueOf("특이사항")); 
					cell.setCellStyle(columnStyle);
					sheet.addMergedRegion(new CellRangeAddress(0,1,lastColCnt, lastColCnt));
					rowIndex++;
					
					ArrayList<String> keyList = new ArrayList<String>();
					
					keyList.add("oi_code_nm");
					for (Map<String, String> map : firstColumnList) {
						//System.out.println("map : "+ map);
						keyList.add("pog_"+map.get("oi_code"));
						keyList.add("cur_"+map.get("oi_code"));
					}
					keyList.add("dfap_pog_rate");
					keyList.add("dfap_cur_rate");
					keyList.add("partclr_matter");
					
					
					
					
					for (Map<String, String> bodyMap : bodyList) { 
						row = sheet.createRow((short) rowIndex);
						if (firstColumnList != null && firstColumnList.size() > 0) {
							
							for (int i = 0; i < keyList.size(); i++) { 
								cell = row.createCell(i);
								String key = keyList.get(i);
								cell.setCellValue(String.valueOf(StringCheck.nullToRplace(bodyMap.get(key),"")));
								if(i < 1){
									cell.setCellStyle(centerStyle);
								}else{
									cell.setCellStyle(numberStyle);
								}
							}
						}
						rowIndex++;
					}
				}
			}else if(s == 1 || s == 2){
				
				firstColumnList = columnLists.get(s);
				bodyList = bodyLists.get(s);
//				System.out.println("firstColumnList : "+ firstColumnList.size());
//				System.out.println("bodyList : "+ firstColumnList.size());
				
				int firstColLen = firstColumnList.size(); 
				int bodyLen = bodyList.size();
				
				if ((firstColumnList != null && firstColLen > 0) && (bodyList != null && bodyLen > 0)) {
					
					int rowIndex = 0;
					
					// 컬럼 생성
					row = sheet.createRow((short) rowIndex);
					
					
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("구분")); 
					cell.setCellStyle(columnStyle);
					for (int i = 0; i < firstColLen; i++) {
						
						String columnName = firstColumnList.get(i).get("oi_code_nm");
//						System.out.println("columnName : "+ columnName); 
						
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(columnName)); 
						cell.setCellStyle(columnStyle);
					}
					rowIndex++;
					
					ArrayList<String> keyList = new ArrayList<String>();
					
					keyList.add("oi_code_nm");
					for (Map<String, String> map : firstColumnList) {
						System.out.println("map : "+ map);
						keyList.add(map.get("oi_code"));
					}
					for (Map<String, String> bodyMap : bodyList) { 
						row = sheet.createRow((short) rowIndex);
						if (firstColumnList != null && firstColumnList.size() > 0) {
							
							for (int i = 0; i < keyList.size(); i++) { 
								cell = row.createCell(i);
								String key = keyList.get(i);
								cell.setCellValue(String.valueOf(StringCheck.nullToRplace(bodyMap.get(key),"")));
								if(i < 1){
									cell.setCellStyle(centerStyle);
								}else{
									cell.setCellStyle(numberStyle);
								}
							}
						}
						rowIndex++;
					}
//					if(s == 1 || s == 2){
						row = sheet.createRow((short) rowIndex);
						for (int i = 0; i <= firstColumnList.size(); i++) {
							cell = row.createCell(i);
							cell.setCellStyle(centerStyle);
						}
						cell = row.createCell(0);
						cell.setCellValue(String.valueOf("특이사항"));
						cell.setCellStyle(centerStyle);
						cell = row.createCell(1);
						cell.setCellValue(String.valueOf(matters[s]));
						cell.setCellStyle(centerStyle);
						sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,1, firstColumnList.size()));
						
//					}
				}
			}else if(s == 3){
				
				if ((trtList != null && trtList.size() > 0)) {
					int trtLen = trtList.size(); 
					row = sheet.createRow((short) 0);
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("구분")); 
					cell.setCellStyle(columnStyle);
					for (int i = 0; i < trtLen; i++) {
						ActivityRndTrtVo trtVo =  trtList.get(i);
						String pm_code_nm = trtVo.getPm_code_nm() ;
						
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(pm_code_nm)); 
						cell.setCellStyle(columnStyle);
					}
					row = sheet.createRow((short) 1);
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("취급여부")); 
					cell.setCellStyle(centerStyle);
					for (int i = 0; i < trtLen; i++) {
						ActivityRndTrtVo trtVo =  trtList.get(i);
						String trtmnt_at = trtVo.getTrtmnt_at() ;
						String checked ="";
						if(trtmnt_at.equals("Y")){
							checked = "√";
						}
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(checked)); 
						cell.setCellStyle(centerStyle);
					}
				}
			}else if(s == 4){
				
				
				
				int oddLen = oddList.size(); 
				if ((trtList != null && oddLen > 0)) {
					XSSFRow row1 = sheet.createRow((short) 0);
					XSSFRow row2 = sheet.createRow((short) 1);
					XSSFRow row3 = sheet.createRow((short) 2);
					XSSFRow row4 = sheet.createRow((short) 3);
					
					row1.setHeight((short)4000);
					cell = row1.createCell(0);
					cell.setCellValue(String.valueOf("당사")); 
					cell.setCellStyle(columnStyle);
					cell = row2.createCell(0);
					cell.setCellStyle(columnStyle);
					row3.setHeight((short)4000);
					cell = row3.createCell(0);
					cell.setCellValue(String.valueOf("경쟁사")); 
					cell.setCellStyle(columnStyle);
					cell = row4.createCell(0);
					cell.setCellStyle(columnStyle);
					
					for (int i = 0; i < 5; i++) {
						sheet.setColumnWidth(i+1, (short)5000);
						cell = row1.createCell(i+1);
						cell.setCellStyle(centerStyle);
						cell = row2.createCell(i+1);
						cell.setCellStyle(centerStyle);
						cell = row3.createCell(i+1);
						cell.setCellStyle(centerStyle);
						cell = row4.createCell(i+1);
						cell.setCellStyle(centerStyle);
					}
					int cnt1= 0;
					int cnt2= 0;
					for (int i = 0; i < oddLen; i++) {
						ActivityRndOddVo trtVo =  oddList.get(i);
						String matter = trtVo.getDrop_partclr_matter();
						String flag = trtVo.getDrop_flag();
						
						
						String imgUrl = trtVo.getDrop_img_url();
						String root = request.getSession().getServletContext().getRealPath("/");
						
						
						InputStream inputStream = new FileInputStream(root+imgUrl);
						byte[] bytes = IOUtils.toByteArray(inputStream);
						int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
						System.out.println("pictureIdx : " + pictureIdx);
						inputStream.close();
						CreationHelper helper = workbook.getCreationHelper();
						Drawing drawing = sheet.createDrawingPatriarch();
						ClientAnchor anchor = helper.createClientAnchor();
						anchor.setDx1(2 * XSSFShape.EMU_PER_PIXEL);
						anchor.setDy1(2 * XSSFShape.EMU_PER_PIXEL);
						if(flag.equals("1")){
							if(pictureIdx >= 0 ){
								anchor.setRow1(0);
								anchor.setCol1(cnt1+1);
								Picture pict = drawing.createPicture(anchor, pictureIdx);
								pict.resize(0.34);
							}
							
							cell = row2.createCell(cnt1+1);
							cell.setCellValue(String.valueOf(matter)); 
							cnt1++;
						}else{
							if(pictureIdx >= 0 ){
								anchor.setRow1(2);
								anchor.setCol1(cnt2+1);
								Picture pict = drawing.createPicture(anchor, pictureIdx);
								pict.resize(0.34);
							}
							
							cell = row4.createCell(cnt2+1);
							cell.setCellValue(String.valueOf(matter)); 
							
							cnt2++;
						}
						
					}
				}
			}
		}
		
		uploadServer(workbook);
	}
	public void makeListExcelRnd(ExcelVo vo) throws Exception{
		
		
		
		this.fixingTrtList = vo.getFixingTrtList();
		this.columnLists = vo.getColumnLists();
		this.bodyLists = vo.getBodyLists();
		this.oFileName = vo.getFileName();
		this.sheetNames = vo.getSheetNames();
		this.matters = vo.getMatters();
		
		workbook = new XSSFWorkbook(); 				// 1차로 workbook을 생성
		
		//바디 굵은선 금액 스타일
		XSSFCellStyle numberStyle = workbook.createCellStyle();
		numberStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		numberStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		numberStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberStyle.setBorderRight(CellStyle.BORDER_THIN);
		numberStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		XSSFCellStyle centerStyle = workbook.createCellStyle();
		centerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		centerStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		centerStyle.setBorderTop(CellStyle.BORDER_THIN);
		centerStyle.setBorderLeft(CellStyle.BORDER_THIN);
		centerStyle.setBorderRight(CellStyle.BORDER_THIN);
		centerStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
//		XSSFCellStyle bodyStyle = workbook.createCellStyle();
		
		XSSFCellStyle columnStyle = workbook.createCellStyle();
		columnStyle.setFillForegroundColor( IndexedColors.GREY_25_PERCENT.getIndex());
		columnStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		columnStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		columnStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		columnStyle.setBorderTop(CellStyle.BORDER_THIN);
		columnStyle.setBorderLeft(CellStyle.BORDER_THIN);
		columnStyle.setBorderRight(CellStyle.BORDER_THIN);
		columnStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		
		for (int s = 0; s < sheetNames.length; s++) {
			sheet = workbook.createSheet(sheetNames[s]); 	// 2차는 sheet생성
			
			if(s < 3){
				
				firstColumnList = columnLists.get(s);
				bodyList = bodyLists.get(s);
//				System.out.println("firstColumnList : "+ firstColumnList.size());
//				System.out.println("bodyList : "+ firstColumnList.size());
				
				int firstColLen = firstColumnList.size(); 
				int bodyLen = bodyList.size();
				
				if ((firstColumnList != null && firstColLen > 0) && (bodyList != null && bodyLen > 0)) {
					
					int rowIndex = 0;
					
					// 컬럼 생성
					row = sheet.createRow((short) rowIndex);
					
					
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("구분")); 
					cell.setCellStyle(columnStyle);
					for (int i = 0; i < firstColLen; i++) {
						
						String columnName = firstColumnList.get(i).get("oi_code_nm");
//						System.out.println("columnName : "+ columnName); 
						
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(columnName)); 
						cell.setCellStyle(columnStyle);
					}
					rowIndex++;
					
					ArrayList<String> keyList = new ArrayList<String>();
					
					keyList.add("oi_code_nm");
					for (Map<String, String> map : firstColumnList) {
						System.out.println("map : "+ map);
						keyList.add(map.get("oi_code"));
					}
					for (Map<String, String> bodyMap : bodyList) { 
						row = sheet.createRow((short) rowIndex);
						if (firstColumnList != null && firstColumnList.size() > 0) {
							
							for (int i = 0; i < keyList.size(); i++) { 
								cell = row.createCell(i);
								String key = keyList.get(i);
								cell.setCellValue(String.valueOf(StringCheck.nullToRplace(bodyMap.get(key),"")));
								if(i < 1){
									cell.setCellStyle(centerStyle);
								}else{
									cell.setCellStyle(numberStyle);
								}
							}
						}
						rowIndex++;
					}
					if(s == 1 || s == 2){
						row = sheet.createRow((short) rowIndex);
						for (int i = 0; i <= firstColumnList.size(); i++) {
							cell = row.createCell(i);
							cell.setCellStyle(centerStyle);
						}
						cell = row.createCell(0);
						cell.setCellValue(String.valueOf("특이사항"));
						cell.setCellStyle(centerStyle);
						cell = row.createCell(1);
						cell.setCellValue(String.valueOf(matters[s]));
						cell.setCellStyle(centerStyle);
						sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,1, firstColumnList.size()));
						
					}
				}
			}else if(s == 3){
				
				if ((fixingTrtList != null && fixingTrtList.size() > 0)) {
					int trtLen = fixingTrtList.size(); 
					row = sheet.createRow((short) 0);
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("구분")); 
					cell.setCellStyle(columnStyle);
					for (int i = 0; i < trtLen; i++) {
						ActivityFixingTrtVo trtVo =  fixingTrtList.get(i);
						String pm_code_nm = trtVo.getPm_code_nm() ;
						
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(pm_code_nm)); 
						cell.setCellStyle(columnStyle);
					}
					row = sheet.createRow((short) 1);
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf("취급여부")); 
					cell.setCellStyle(centerStyle);
					for (int i = 0; i < trtLen; i++) {
						ActivityFixingTrtVo trtVo =  fixingTrtList.get(i);
						String trtmnt_at = trtVo.getTrtmnt_at() ;
						String checked ="";
						if(trtmnt_at.equals("Y")){
							checked = "√";
						}
						cell = row.createCell(i+1);
						cell.setCellValue(String.valueOf(checked)); 
						cell.setCellStyle(centerStyle);
					}
				}
			}else if(s == 4){
				
				
				
				if ((trtList != null && oddList.size() > 0)) {
					int oddLen = oddList.size(); 
					XSSFRow row1 = sheet.createRow((short) 0);
					XSSFRow row2 = sheet.createRow((short) 1);
					XSSFRow row3 = sheet.createRow((short) 2);
					XSSFRow row4 = sheet.createRow((short) 3);
					
					row1.setHeight((short)4000);
					cell = row1.createCell(0);
					cell.setCellValue(String.valueOf("당사")); 
					cell.setCellStyle(columnStyle);
					cell = row2.createCell(0);
					cell.setCellStyle(columnStyle);
					row3.setHeight((short)4000);
					cell = row3.createCell(0);
					cell.setCellValue(String.valueOf("경쟁사")); 
					cell.setCellStyle(columnStyle);
					cell = row4.createCell(0);
					cell.setCellStyle(columnStyle);
					
					for (int i = 0; i < 5; i++) {
						sheet.setColumnWidth(i+1, (short)5000);
						cell = row1.createCell(i+1);
						cell.setCellStyle(centerStyle);
						cell = row2.createCell(i+1);
						cell.setCellStyle(centerStyle);
						cell = row3.createCell(i+1);
						cell.setCellStyle(centerStyle);
						cell = row4.createCell(i+1);
						cell.setCellStyle(centerStyle);
					}
					int cnt1= 0;
					int cnt2= 0;
					for (int i = 0; i < oddLen; i++) {
						ActivityRndOddVo trtVo =  oddList.get(i);
						String matter = trtVo.getDrop_partclr_matter();
						String flag = trtVo.getDrop_flag();
						
						
						String imgUrl = trtVo.getDrop_img_url();
						String root = request.getSession().getServletContext().getRealPath("/");
						
						
						InputStream inputStream = new FileInputStream(root+imgUrl);
						byte[] bytes = IOUtils.toByteArray(inputStream);
						int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
						System.out.println("pictureIdx : " + pictureIdx);
						inputStream.close();
						CreationHelper helper = workbook.getCreationHelper();
						Drawing drawing = sheet.createDrawingPatriarch();
						ClientAnchor anchor = helper.createClientAnchor();
						anchor.setDx1(2 * XSSFShape.EMU_PER_PIXEL);
						anchor.setDy1(2 * XSSFShape.EMU_PER_PIXEL);
						if(flag.equals("1")){
							if(pictureIdx >= 0 ){
								anchor.setRow1(0);
								anchor.setCol1(cnt1+1);
								Picture pict = drawing.createPicture(anchor, pictureIdx);
								pict.resize(0.34);
							}
							
							cell = row2.createCell(cnt1+1);
							cell.setCellValue(String.valueOf(matter)); 
							cnt1++;
						}else{
							if(pictureIdx >= 0 ){
								anchor.setRow1(2);
								anchor.setCol1(cnt2+1);
								Picture pict = drawing.createPicture(anchor, pictureIdx);
								pict.resize(0.34);
							}
							
							cell = row4.createCell(cnt2+1);
							cell.setCellValue(String.valueOf(matter)); 
							
							cnt2++;
						}
						
					}
				}
			}
		}
		
		uploadServer(workbook);
	}
	
	private String getColumnId(int idx){
		int paramIdx = idx;
		String key[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","A"};
		int keyLen = (key.length)-1;
		String id = "";
		while(true) {
			int  thisIdx = 0;
			thisIdx =  paramIdx / keyLen;
			 if(thisIdx > 0){
				 id += key[thisIdx-1];
				 paramIdx = paramIdx % keyLen;
			 }else{
				 id += key[idx % keyLen];
				break;
			 }
			
		}
		return id;
	}

	private void uploadServer(XSSFWorkbook targetWorkBook){
		
		
		String date = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		String dirY  = new SimpleDateFormat("yyyy").format(new Date()); 
		String dirM  = new SimpleDateFormat("MM").format(new Date()); 
		String dirD  = new SimpleDateFormat("dd").format(new Date()); 
		
	
		sFileName = date; // +(oFileName.substring(oFileName.lastIndexOf(".")));
	//	String root = "/";
		String root = request.getSession().getServletContext().getRealPath("/");
		String filePath = root +BASEFOLDER+SP+folder +SP+ dirY+SP+ dirM+SP+ dirD ;
		
		sFileName = checkFile(filePath,sFileName+fileExt);
		
		fullPath = filePath + SP + sFileName;
		
		FileOutputStream fileoutputstream = null;
		try {
			checkDir(filePath);
			fileoutputstream = new FileOutputStream(fullPath);
			targetWorkBook.write(fileoutputstream);
			fileoutputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 필수로 닫아주어야함
		//System.out.println("엑셀파일생성성공");
	}
	private String checkFile(String path, String fileNm){
		String tempFileName =  fileNm.substring(0,fileNm.lastIndexOf("."));
		String tempFileEx =  fileNm.substring(fileNm.lastIndexOf("."));
		String tempFullName =  tempFileName+tempFileEx;
		int cnt = 1;
		while (true) {
			File f = new File(path+SP+tempFullName);
			if (f.isFile()) {
				//System.out.println(tempFullName+ "와 중복된 파일명이 있습니다.");
				tempFullName = tempFileName+"("+cnt+")"+tempFileEx;
				cnt++;
			}else {
				//System.out.println(tempFullName + "는 중복된 파일명이 없습니다.");
				break;
			}
	    }
	    return tempFullName;
	}
	private void checkDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
}
