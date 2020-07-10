package com.record.MyGameRecord.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.record.MyGameRecord.member.dao.MemberDao;
import com.record.MyGameRecord.member.vo.Member;

@Service("mService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDao mDao;

	@Override
	public Member loginMember(Member m) {

		return mDao.selectMember(m);
	}
	
}
