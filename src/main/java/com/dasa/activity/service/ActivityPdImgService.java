package com.dasa.activity.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.dasa.activity.dao.ActivityPdImgDAO;
import com.dasa.activity.vo.ActivityPdImgVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.vo.NaviVo;

public class ActivityPdImgService extends SqlSessionDaoSupport implements ActivityPdImgDAO {

	@Autowired
	private AttachManager attachManager;

	@Override
	public NaviVo fixCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("pdImg.fixCnt" ,naviVo));
		return naviVo;
	}

	@Override
	public List<Map<String, Object>> fixList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("pdImg.fixList", naviVo);
	}
	
	@Override
	public int m_fixPdImgSave(ActivityPdImgVo vo) throws SQLException,Exception {
		int cnt = 0;
		
		MultipartFile[] files = vo.getFiles();
		for (MultipartFile file :files) {
			try {
				if(file.getBytes().length > 0){
					String imgUrl = attachManager.uploadPdImg(file);
					vo.setPd_img_url(imgUrl);
					cnt = getSqlSession().insert("pdImg.fixPdImgSave", vo);
				}
			} catch (Exception e) {
				e.getStackTrace();
				System.out.println("파일저장실패");
			}
		}
		return cnt;
	}

	@Override
	public NaviVo rndCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("pdImg.rndCnt" ,naviVo));
		return naviVo;
	}

	@Override
	public List<Map<String, Object>> rndList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("pdImg.rndPdImgList", naviVo);
	}

	@Override
	public int m_rndPdImgSave(ActivityPdImgVo vo) throws SQLException,Exception {
		int cnt = 0;
		
		MultipartFile[] files = vo.getFiles();
		for (MultipartFile file :files) {
			try {
				if(file.getBytes().length > 0){
					String imgUrl = attachManager.uploadPdImg(file);
					vo.setPd_img_url(imgUrl);
					cnt = getSqlSession().insert("pdImg.rndPdImgSave", vo);
				}
			} catch (Exception e) {
				e.getStackTrace();
				System.out.println("파일저장실패");
			}
		}
		return cnt;
	}

}
