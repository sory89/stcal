package com.stcal.manager;

import java.sql.Connection;
import java.util.List;

/**
 * User: Jean
 * Date: 22/01/2014
 */

public interface Manager<Bean> {

    /**
     * Cree un nouvel element<br />
     * Equivalent à INSERT
     * @param nouveau obj a inserer
     * @return id de l'objet
     */
    public int create(Bean nouveau);

    /**
     * Lit un objet dans la base de donne
     * @param id id de l'objet à lire
     * @return obj lu
     */
    public Bean read(int id);

    /**
     * Lit tout les elements
     * equivalent à SELECT *
     * @return Liste d'obj lu
     */
    public List<Bean> readall();

    /**
     * Met à jour un objet dans la base de donne
     * @param table obj à mettre à jour
     * @return nombre de ligne modifié
     */
    public int update(Bean table);

    /**
     * Supprime un objet de la base de donné<br />
     * équivalent à DELETE
     * @param id id de l'objet à supprimer
     * @return nombre de ligne changé
     */
    public int delete(int id);

}
