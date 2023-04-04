package cz.cvut.kbss.jopa.multipleinheritancedemo.service;

import cz.cvut.kbss.jopa.multipleinheritancedemo.model.DataFormat;
import cz.cvut.kbss.jopa.multipleinheritancedemo.persistence.dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DataService {

    private final DataDao dataDao;

    @Autowired
    public DataService(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    public String getRepositoryData(String format) {
        Objects.requireNonNull(format);
        final DataFormat df = DataFormat.fromString(format);
        return dataDao.getRepositoryData(df);
    }
}

