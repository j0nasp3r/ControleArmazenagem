/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Estado;
import dao.EstadoDao;
import java.util.ArrayList;

/**
 *
 * @author j0nas
 */
public class EstadoController {
    EstadoDao estadoDao;

    public EstadoController() {
        if (estadoDao == null) {
            estadoDao = new EstadoDao();
        }
    }

    public boolean insereCadastroEstado(Estado estado) {
        if (estado.getIdEstado() != 0) {
            return estadoDao.updateCadastroEstado(estado);
        } else {
            return estadoDao.insereCadastroEstado(estado);
        }
    }

    public ArrayList<Estado> buscaCadastroEstado() {
        return estadoDao.buscaCadastroEstado();
    }

    public boolean excluirCadastroEstado(int id) {
        return estadoDao.excluirCadastroEstado(id);
    }
    
    public String buscarRetornoPesqEmp(int idEstado) {
        return estadoDao.buscarRetornoPesqEmp(idEstado);
    }
}
