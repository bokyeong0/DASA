package com.dasa.communication.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.communication.vo.ReplyVo;

public interface ReplyDAO {
	public int replyCount(Map<String, String> map) throws SQLException;
	
	public List<ReplyVo> replyList(String bmInnb, String loginNo, String authReply) throws SQLException;

	@Transactional
	public String replySave(ReplyVo replyVo) throws SQLException;
	
	public int replyDelete(Map<String, String> map) throws SQLException;
}
