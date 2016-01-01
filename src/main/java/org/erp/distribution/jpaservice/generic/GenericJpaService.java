/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erp.distribution.jpaservice.generic;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author bagus
 */
public interface GenericJpaService<T, ID extends Serializable> {
     public List<T> findAll() throws DataAccessException;
     public List<T> findAllByField(String fieldName, String strValue, Long longValue) throws DataAccessException;
     public List<T> findAllDetilByRefno(Long longValue) throws DataAccessException;
     
     public T findById(Serializable ID) throws DataAccessException;
     public List<T> findAllById(Serializable ID) throws DataAccessException;
     public void createObject(T domain) throws DataAccessException;
     public void updateObject(T domain) throws DataAccessException;
     public void removeObject(T domain) throws DataAccessException;
     public long count() throws DataAccessException;
}
