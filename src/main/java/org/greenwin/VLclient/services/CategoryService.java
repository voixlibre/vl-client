package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Category;
import org.greenwin.VLclient.proxies.CategoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryProxy categoryProxy;

    public Category getCategoryById(int id){
        return categoryProxy.getCategoryById(id);
    }

    public List<Category> getAllCategories(){
        return categoryProxy.getAllCategories();
    }
}
