package ru.iambelyaev.coincontrolserver.restapi.service;

import ru.iambelyaev.coincontrolserver.ResultInfo;
import ru.iambelyaev.coincontrolserver.restapi.model.Category;

import java.util.List;

public interface CategoryService {
    /**
     * Создает новую категорию
     * @param Category - категория для создания
     */
    ResultInfo create(Category Category);

    /**
     * Возвращает список всех имеющихся категорий
     * @return список категорий
     */
    List<Category> readAll(int userId);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param Category - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    ResultInfo update(Category Category);

    /**
     * Удаляет категорию с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean categoryDelete(Integer userId, Integer categoryId);
}
