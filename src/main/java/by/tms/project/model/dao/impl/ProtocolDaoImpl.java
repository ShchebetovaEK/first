package by.tms.project.model.dao.impl;

import by.tms.project.exception.DaoException;
import by.tms.project.model.dao.AbstractDao;
import by.tms.project.model.dao.ProtocolDao;
import by.tms.project.model.entity.Protocol;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProtocolDaoImpl extends AbstractDao<Long, Protocol> implements ProtocolDao {

    @Override
    public List<Protocol> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Protocol> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Protocol entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Protocol entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Protocol entity) throws DaoException {
        return false;
    }

    @Override
    public List<Protocol> findAllByData(LocalDate protocolData) throws DaoException {
        return null;
    }

    @Override
    public List<Protocol> findAllProtocolPayer(String payer) throws DaoException {
        return null;
    }

    @Override
    public List<Protocol> findByCost(Long cost) throws DaoException {
        return null;
    }
}
