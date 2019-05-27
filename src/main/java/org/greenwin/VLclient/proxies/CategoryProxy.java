package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.greenwin.VLclient.values.ValueType.*;


@Component
@FeignClient(name = ZUUL_SERVER_NAME, url = ZUUL_LOCALHOST + MS_CAMPAIGN + API_CATEGORIES)
public interface CategoryProxy {

    @GetMapping("/id/{id}")
    Category getCategoryById(@PathVariable("id") int id);

    @GetMapping("/")
    List<Category> getAllCategories();
}
