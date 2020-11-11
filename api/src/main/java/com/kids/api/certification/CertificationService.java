package com.kids.api.certification;

public interface CertificationService {
    int addCertification(Certification certification);

    Certification getCertificationByNo(String certificationNo);

    int createKidsAuth(KidsAuth kidsAuth);
    
    boolean isDuplicate(int kidId);
    
    int updateCertification(Certification certification);
    
    int deleteKidAuth(int kidId);
    
    boolean isDuplicateKid(int kidId);
}
