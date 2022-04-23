package com.dasa.communication.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.communication.dao.ReplyDAO;
import com.dasa.communication.vo.ReplyVo;

public class ReplyService extends SqlSessionDaoSupport implements ReplyDAO {

	@Override
	public int replyCount(Map<String,String> map) throws SQLException {
		return getSqlSession().selectOne("Reply.replyCount", map);
	}
	@Override
	public List<ReplyVo> replyList(String bmInnb, String loginNo, String authReply) throws SQLException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bm_innb", bmInnb);
		map.put("regist_man", loginNo);
		List<ReplyVo> replyArr = getSqlSession().selectList("Reply.replyList", map);
		
		return getReplyChild(replyArr, authReply, null, loginNo);
	}
	
	private List<ReplyVo> getReplyChild(List<ReplyVo> replyArr, String authReply, String parent_br_innb, String loginNo) {
		List<ReplyVo> replySortedList = new ArrayList<ReplyVo>();
		
		for (ReplyVo reply : replyArr) {
			if((parent_br_innb == null && reply.getBr_depth().equals("1"))
					|| (parent_br_innb != null && reply.getParent_br_innb()!=null && reply.getParent_br_innb().equals(parent_br_innb)))
			{
				List<ReplyVo> childArr = getReplyChild(replyArr, authReply, reply.getBr_innb(), loginNo);
				if(childArr.size() > 0) { // 답글이 달린 댓글은 답변 못달게..
					reply.setAuth_reply("N");
					replySortedList.add(reply);
					replySortedList.addAll(childArr);
				}
				else {
					int br_depth = Integer.parseInt(reply.getBr_depth());
					if(br_depth != 1) { // 답변에는 댓글이 없다.. 만약 답글에 댓글을 단다면 2배수로 조절
						reply.setAuth_reply("N");
					}
					else {
						reply.setAuth_reply(authReply);
					}
					replySortedList.add(reply);
				}
			}
		}
		
		return replySortedList;
	}

	@Override
	public String replySave(ReplyVo replyVo) throws SQLException { 
		int cnt = getSqlSession().insert("Reply.replyInsert", replyVo);
		return cnt > 0 ? replyVo.getBm_innb():null;
	}

	@Override
	public int replyDelete(Map<String,String> map) throws SQLException {
		return getSqlSession().update("Reply.replyDelete", map);
	}
}
