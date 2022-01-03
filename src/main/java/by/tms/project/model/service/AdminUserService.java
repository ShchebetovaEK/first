package by.tms.project.model.service;

import by.tms.project.exception.ServiceException;
import by.tms.project.model.entity.Entity;
import by.tms.project.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Map;

public interface AdminUserService {

    void edit(Entity entity, String commandName) throws ServiceException;

    void delete(long id, String commandName) throws ServiceException;

    void createAdmin(User user, HttpServletRequest request) throws ServiceException;

    void closeProtocol(Long protocolId, Long patientId, Long doctorId, BigDecimal protocolCost, HttpSession session) throws ServiceException;

    void createProtocol(Map<String, String> createProtocolMap, HttpSession response) throws ServiceException;


}
