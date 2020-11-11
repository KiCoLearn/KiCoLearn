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
        return temp.insert(ns + "insert", certification);
    }

    @Override
    public Certification getCertificationByNo(String certificationNo) {
        return temp.selectOne(ns + "search", certificationNo);
    }

    @Override
    public int createKidsAuth(KidsAuth kidsAuth) {
        return temp.insert(ns + "password", kidsAuth);
    }

    @Override
    public boolean isDuplicate(int kidId) {
        if(temp.selectOne(ns+"isDuplicate", kidId)!=null) return true;
        return false;
    }

    @Override
    public int updateCertification(Certification certification) {
        return temp.update(ns+"update", certification);
    }

    @Override
    public int deleteKidAuth(int kidId) {
        return temp.delete(ns+"delete",kidId);
    }

    @Override
    public boolean isDuplicateKid(int kidId) {
        if(temp.selectOne(ns+"isDuplicateKid", kidId)!=null) return true;
        return false;
    }

}
