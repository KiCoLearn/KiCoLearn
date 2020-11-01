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

}
