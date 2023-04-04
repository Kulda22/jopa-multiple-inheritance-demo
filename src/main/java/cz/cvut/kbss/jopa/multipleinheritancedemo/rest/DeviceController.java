package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Device;
import cz.cvut.kbss.jopa.multipleinheritancedemo.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/devices")
public class DeviceController {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceController.class);

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getDevices() {
        return deviceService.findAll();
    }

}
