/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Pessoa;
import dao.PessoaDao;
import java.util.ArrayList;

/**
 *
 * @author j0nas
 */
public class PessoaController {

        PessoaDao pessoaDao;

        public PessoaController() {
            if (pessoaDao == null) {
                pessoaDao = new PessoaDao();
            }
        }

        public boolean insereCadastroPessoa(Pessoa pessoa) {
            if (pessoa.getIdPessoa() != 0) {
                return pessoaDao.updateCadastroPessoa(pessoa);
            } else {
                return pessoaDao.insereCadastroPessoa(pessoa);
            }
        }

        public ArrayList<Pessoa> buscaCadastroPessoa() {
            return pessoaDao.buscaCadastroPessoa();
        }

        public boolean excluirCadastroPessoa(int id) {
            return pessoaDao.excluirCadastroPessoa(id);
        }
    }
