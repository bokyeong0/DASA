package com.dasa.activity.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.dasa.activity.dao.EventMonthDAO;
import com.dasa.activity.vo.ActivityEventMonthVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.vo.NaviVo;

public class EventMonthService extends SqlSessionDaoSupport implements EventMonthDAO {

	@Autowired
	private AttachManager attachManager;
	
	@Override
	public NaviVo emmListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("emmListCnt" ,naviVo));
		return naviVo;
	}

	@Override
	public List<Map<String, Object>> emmList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("emmList", naviVo);
	}
	
	@Override
	public int emmSave(Map<String, Object> param) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().insert("emmSave", param);
		return cnt;
	}

	@Override
	public Map<String, Object> emmView(Map<String, Object> param) throws SQLException {
		return  getSqlSession().selectOne("emmView", param);
	}

	@Override
	public int emmDel(Map<String, Object> param) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().update("emmDel", param);
		return cnt;
	}

	@Override
	public int emmUpt(Map<String, Object> param) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().update("emmUpt", param);
		return cnt;
	}
	
	@Override
	public List<Map<String, Object>> emiSmList(Map<String, Object> param) throws SQLException {
		return getSqlSession().selectList("emiSmList", param);
	}

	@Override
	public NaviVo emiListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("emiListCnt" ,naviVo));
		return naviVo;
	}

	@Override
	public List<Map<String, Object>> emiList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("emiList", naviVo);
	}
	
	@Override
	public int emiDel(Map<String, Object> param) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().update("emiDel", param);
		return cnt;
	}

	@Override
	public int m_fileUpload(ActivityEventMonthVo vo) {
		int cnt = 0;
		
		MultipartFile[] files = vo.getFiles();
		for (MultipartFile file :files) {
			try {
				if(file.getBytes().length > 0){
					String imgUrl = attachManager.uploadEventMonth(file);
					vo.setEmi_img_url(imgUrl);
					cnt = getSqlSession().insert("emiSave", vo);
				}
			} catch (Exception e) {
				e.getStackTrace();
				System.out.println("파일저장실패");
			}
		}
		return cnt;
	}
	
}
