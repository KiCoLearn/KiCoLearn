package com.kids.api.account.parents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kids.api.account.AccountService;
import com.kids.api.account.User;
import com.kids.api.account.oauth.OAuth2Service;

@Service("parentsAccountService")
public class ParentsAccountService<T extends User> implements AccountService<T> {
    @Autowired
    ParentsRepository parentsRepository;

    @Autowired
    OAuth2Service oauthService;

    @Override
    public T login(T request) {
        Parents p = (Parents) request;
        Parents parents = null;
        try {
            System.out.println(p.toString());
            List<Parents> list = parentsRepository.getParentsByUID(p);
            System.out.println(list.toString());
            if (list.size() == 1) {
                parents = list.get(0);
                
            } else {
                User userInformation = oauthService.getUserInformation(p.getToken(), request.getProvider());

                parents = Parents.builder()
                                 .uuid(userInformation.getUuid())
                                 .provider(userInformation.getProvider())
                                 .name(userInformation.getName())
                                 .build();
                
                int count = parentsRepository.insert(parents);
                
                parents = parentsRepository.getParentsByUID(parents).get(0);
            }
            
            if (!parents.getEnabled()) {
                parents.setEnabled(true);
                parents.setDisableTime(null);
                
                int count = parentsRepository.enable((Parents) parents);
            }
            
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return (T) parents;
    }

    @Transactional
    @Override
    public long delete(T request) {
        Parents p = (Parents) request;
        Parents parents = null;
        try {
            parents = parentsRepository.getParentsByUID((Parents) request).get(0);
            
            if (parents.getEnabled()) {
                int count = parentsRepository.unlink(parents);
            } else {
                return 0;
            }
            
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
        
        return request.getUuid();
    }

    @Override
    public int add(T object) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public T detail(int kidId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> getKidsByParentId(int parentId) {
        // TODO Auto-generated method stub
        return null;
    }

}
