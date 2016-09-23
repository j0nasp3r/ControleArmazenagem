/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Empresa;
import dao.EmpresaDao;
import java.util.ArrayList;

/**
 *
 * @author j0nas
 */
public class EmpresaController {
            EmpresaDao empresaDao;

        public EmpresaController() {
            if (empresaDao == null) {
                empresaDao = new EmpresaDao();
            }
        }

        public boolean insereCadastroEmpresa(Empresa empresa) {
            if (empresa.getIdEmpresa() != 0) {
                return empresaDao.updateCadastroEmpresa(empresa);
            } else {
                return empresaDao.insereCadastroEmpresa(empresa);
            }
        }

        public ArrayList<Empresa> buscaCadastroEmpresa() {
            return empresaDao.buscaCadastroEmpresa();
        }

        public boolean excluirCadastroEmpresa(int id) {
            return empresaDao.excluirCadastroEmpresa(id);
        }
}
