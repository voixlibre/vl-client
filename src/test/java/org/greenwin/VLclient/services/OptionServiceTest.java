package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.OptionProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OptionServiceTest {

    @Mock
    private OptionProxy optionProxy;

    @InjectMocks
    private OptionService optionService;

    @Test
    public void saveOption() {
        Option option = new Option();
        optionService.saveOption(option);
        verify(optionProxy, times(1)).saveOption(option);

    }
}
