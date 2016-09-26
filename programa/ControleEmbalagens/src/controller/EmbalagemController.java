/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmbalagemDao;
import Model.Embalagem;
import java.util.ArrayList;

/**
 *
 * @author j0nas
 */
public class EmbalagemController {

    EmbalagemDao embalagemDao;

    public EmbalagemController() {
        if (embalagemDao == null) {
            embalagemDao = new EmbalagemDao();
        }
    }

    public boolean insereCadastroEmbalagem(Embalagem embalagem) {
        if (embalagem.getIdEmbalagem() != 0) {
            return embalagemDao.updateCadastroEmbalagem(embalagem);
        } else {
            return embalagemDao.insereCadastroEmbalagem(embalagem);
        }
    }

    public ArrayList<Embalagem> buscaCadastroEmbalagem() {
        return embalagemDao.buscaCadastroEmbalagem();
    }

    public boolean excluirCadastroEmbalagem(int id) {
        return embalagemDao.excluirCadastroEmbalagem(id);
    }

    public String buscarEmbalagem(int idEmbalagem) {
        return embalagemDao.buscarEmbalagem(idEmbalagem);
    }
}
