package cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Copier;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.office.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDao {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceDao.class);

    private final EntityManager em;

    @Autowired
    public DeviceDao(EntityManager em) {
        this.em = em;
    }

    public List<Device> findAll() {
        return em.createNamedQuery("Device.findAll", Device.class).getResultList();
    }

}
