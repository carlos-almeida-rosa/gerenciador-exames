package com.gerenciadorexames.services.interfaces;
import java.util.List;

public interface CRUDService<T, ID> {

  public List<T> list();

  public T insert(T entity);

  public T listById(ID id);

  public void deleteById(ID id);

  public void updateById(ID id, T entity);

}
