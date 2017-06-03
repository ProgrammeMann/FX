package com.springapp.mvc.services;

import com.springapp.mvc.common.hibernateEntity.GoodInStore;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.common.hibernateEntity.Store;
import com.springapp.mvc.repositories.GoodRepository;
import com.springapp.mvc.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepositoryHibernate;
    @Autowired
    private GoodRepository goodRepositoryHibernate;

    public String updateGoodCount(Long goodInStoreId, int count) {
        GoodInStore goodInStore = storeRepositoryHibernate.getGoodInStoreById(goodInStoreId);
        goodInStore.setCount(count);
        storeRepositoryHibernate.updateGoodInStore(goodInStore);
        return "success";
    }

    public void migrateGoods(Long goodId, Long storeFrom, Long storeTo) {
        GoodInStore goodInStoreFrom = storeRepositoryHibernate.getGoodInStoreByGoodIdAndStoreId(goodId, storeFrom);
        GoodInStore goodInStoreTo = storeRepositoryHibernate.getGoodInStoreByGoodIdAndStoreId(goodId, storeTo);
        Store newStore = storeRepositoryHibernate.getStoreById(storeTo);
        if (goodInStoreFrom.getCount() != 0) {
            if ((goodInStoreTo == null) || (goodInStoreTo.getCount() == 0)) {
                goodInStoreFrom.setStore(newStore);
            } else {
                Integer newCount = goodInStoreFrom.getCount() + goodInStoreTo.getCount();
                goodInStoreFrom.setCount(newCount);
                goodInStoreFrom.setStore(newStore);
            }
            storeRepositoryHibernate.addGoodInStore(goodInStoreFrom);
        }
    }

    public List<GoodInStore> getAllGoodInStore(Long storeId) {
        Store store = storeRepositoryHibernate.getStoreById(storeId);
        return storeRepositoryHibernate.getGoodInStoreByStore(store);
    }

    public List<Store> getAllStores(){
        return storeRepositoryHibernate.getAll();
    }
}
