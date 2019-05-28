package org.greenwin.VLclient.services;

import org.greenwin.VLclient.proxies.CategoryProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryProxy categoryProxy;

    @InjectMocks
    private CategoryService categoryService;


    @Test
    public void getCategoryById() {
        categoryService.getCategoryById(1);
        verify(categoryProxy, times(1)).getCategoryById(1);
    }

    @Test
    public void getAllCategories() {
        categoryService.getAllCategories();
        verify(categoryProxy, times(1)).getAllCategories();
    }
}
