package com.kids.api.kidsAccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidsServiceImpl implements KidsService {

    @Autowired
    KidsDao kDao;
    
    @Override
    public int addKid(Kids kid) {
        return kDao.addKid(kid);
    }

    @Override
    public int deleteKid(int kidId) {
        return kDao.deleteKid(kidId);
    }

    @Override
    public Kids detailKid(int kidId) { 
        return kDao.detailKid(kidId);
    }

	@Override
	public List<Kids> getKidsByParentId(int parentId) {
		return kDao.getKidsByParentId(parentId);
	}

}
