package com.kids.api.certification;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CertificationDaoImpl implements CertificationDao {

	private static String ns = "com.kids.api.mapper.Certification.";
    
    @Autowired
    SqlSessionTemplate temp;
    
	@Override
	public int addCertification(Certification certification) {
		return temp.insert(ns+"insert", certification);
	}

	@Override
	public Certification getCertificationByNo(String certificationNo) {
		return temp.selectOne(ns+"search", certificationNo);
	}

}
