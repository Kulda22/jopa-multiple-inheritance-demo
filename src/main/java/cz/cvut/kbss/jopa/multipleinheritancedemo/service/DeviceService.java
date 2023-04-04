package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Device;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.DeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceDao deviceDao;

    @Autowired
    public DeviceService(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Transactional
    public List<Device> findAll() {
        return deviceDao.findAll();
    }
}