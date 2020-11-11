package com.kids.api.certification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    CertificationDao cDao;

    @Override
    public int addCertification(Certification certification) {
        return cDao.addCertification(certification);
    }

    @Override
    public Certification getCertificationByNo(String certificationNo) {
        return cDao.getCertificationByNo(certificationNo);
    }

    @Override
    public int createKidsAuth(KidsAuth kidsAuth) {
        return cDao.createKidsAuth(kidsAuth);
    }

    @Override
    public boolean isDuplicate(int kidId) {
        return cDao.isDuplicate(kidId);
    }

    @Override
    public int updateCertification(Certification certification) {
        return cDao.updateCertification(certification);
    }

    @Override
    public int deleteKidAuth(int kidId) {
        return cDao.deleteKidAuth(kidId);
    }

    @Override
    public boolean isDuplicateKid(int kidId) {
        return cDao.isDuplicateKid(kidId);
    }

}
