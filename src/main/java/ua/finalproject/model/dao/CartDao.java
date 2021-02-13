package ua.finalproject.model.dao;

import ua.finalproject.model.entity.CartDTO;

public interface CartDao extends GenericDao<CartDTO> {
    CartDTO findCartByUserId(int id);
}
