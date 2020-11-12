package com.kids.api.certification;

public interface CertificationDao {
    int addCertification(Certification certification);

    Certification getCertificationByNo(String certificationNo);

    int createKidsAuth(KidsAuth kidsAuth);
    
    boolean isDuplicate(int kidId);
    
    int updateCertification(Certification certification);
    
    boolean isDuplicateKid(int kidId);
    
    int deleteKidAuth(int kidId);
}
