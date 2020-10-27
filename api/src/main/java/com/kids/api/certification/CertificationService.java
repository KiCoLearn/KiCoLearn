package com.kids.api.certification;

public interface CertificationService {
	int addCertification(Certification certification);
	Certification getCertificationByNo(String certificationNo);
	int createKidsAuth(KidsAuth kidsAuth);
}
