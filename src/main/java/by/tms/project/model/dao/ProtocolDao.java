package by.tms.project.model.dao;

import by.tms.project.exception.DaoException;
import by.tms.project.model.entity.Protocol;

import java.time.LocalDate;
import java.util.List;

public interface ProtocolDao {

    public List<Protocol> findAllByData(LocalDate protocolData) throws DaoException;

    public List<Protocol> findAllProtocolPayer(String payer) throws DaoException;

    public List<Protocol> findByCost(Long cost) throws DaoException;


}
