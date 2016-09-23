/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Cidade;
import dao.CidadeDao;
import java.util.ArrayList;

/**
 *
 * @author j0nas
 */
public class CidadeController {

    CidadeDao cidadeDao;

    public CidadeController() {
        if (cidadeDao == null) {
            cidadeDao = new CidadeDao();
        }
    }

    public boolean insereCadastroCidade(Cidade cidade) {
        if (cidade.getIdCidade() != 0) {
            return cidadeDao.updateCadastroCidade(cidade);
        } else {
            return cidadeDao.insereCadastroCidade(cidade);
        }
    }

    public ArrayList<Cidade> buscaCadastroCidade() {
        return cidadeDao.buscaCadastroCidade();
    }

    public boolean excluirCadastroCidade(int id) {
        return cidadeDao.excluirCadastroCidade(id);
    }

    public ArrayList<Cidade> buscaEstadoCidade(String estado) {
        return cidadeDao.buscaEstadoCidade(estado);
    }

    public String buscarRetornoPesqEmp(int idCidade) {
        return cidadeDao.buscarRetornoPesqEmp(idCidade);
    }
}
