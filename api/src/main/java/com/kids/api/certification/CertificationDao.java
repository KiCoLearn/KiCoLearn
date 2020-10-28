package com.kids.api.certification;

public interface CertificationDao {
    int addCertification(Certification certification);

    Certification getCertificationByNo(String certificationNo);

    int createKidsAuth(KidsAuth kidsAuth);
}
